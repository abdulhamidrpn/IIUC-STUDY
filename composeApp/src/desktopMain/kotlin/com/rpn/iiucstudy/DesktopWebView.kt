package com.rpn.iiucstudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import javafx.application.Platform
import javafx.concurrent.Worker
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView
import javax.swing.JPanel


@Composable
fun DesktopWebView(
    modifier: Modifier,
    url: String,
) {
    val jPanel: JPanel = remember { JPanel() }
    val jfxPanel = remember { JFXPanel() }

    SwingPanel(
        factory = {
            jfxPanel.apply {
                Platform.runLater {
                    buildWebView(url)
                }
            }
            jPanel.add(jfxPanel)
            jfxPanel
        },
        modifier = modifier,
    )

    DisposableEffect(url) {
        // Reload the video when the URL changes
        Platform.runLater {
            jfxPanel.buildWebView(url)
        }
        onDispose {
            // Clean up
            jPanel.remove(jfxPanel)
        }
    }
}

// The function to initialize the WebView and load the YouTube video URL
private fun JFXPanel.buildWebView(url: String) {
    Platform.runLater {
        val webView = WebView()
        val webEngine: WebEngine = webView.engine

        webEngine.isJavaScriptEnabled = true
        webEngine.load(url)

        // JavaScript to enable custom fullscreen handling
        val script = """
            document.addEventListener('fullscreenchange', function() {
                if (document.fullscreenElement) {
                    console.log("Entered fullscreen mode.");
                } else {
                    console.log("Exited fullscreen mode.");
                }
            });
        """.trimIndent()

        webEngine.executeScript(script)

        // Apply the scene to the JFXPanel
        val scene = Scene(webView)
        setScene(scene)
    }
}


private fun JFXPanel.buildWebViewDefault(url: String) {
    Platform.runLater {
        val webView = WebView()
        val webEngine = webView.engine

        webEngine.userAgent =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"

        webEngine.isJavaScriptEnabled = true

        // Load the provided URL (for each new video selection)
        webEngine.load(url)

        // JavaScript to hide YouTube controls (Optional)
        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
            if (newState == Worker.State.SUCCEEDED) {
                val script = """
                    setTimeout(function() {
                        var overlaySelectors = [
                            '.ytp-gradient-top',
                            '.ytp-gradient-bottom'
                        ];
                        overlaySelectors.forEach(function(selector) {
                            var element = document.querySelector(selector);
                            if (element !== null) {
                                element.style.display = 'none';
                            }
                        });
                    }, 1000); // Adjust the timeout value as needed
                """.trimIndent()
                webEngine.executeScript(script)
            }
        }

        val scene = Scene(webView)
        setScene(scene)
    }
}
