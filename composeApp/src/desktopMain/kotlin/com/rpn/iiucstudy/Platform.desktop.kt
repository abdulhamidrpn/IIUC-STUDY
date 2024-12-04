package com.rpn.iiucstudy

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.rpn.iiucstudy.data.utils.Constants
import com.rpn.iiucstudy.presentation.utils.Type
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.Desktop
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.URI
import java.nio.file.Paths
import java.util.Locale
import java.util.UUID
import java.util.concurrent.TimeUnit


actual fun getType(): Type = Type.Desktop


actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}

actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore {
        Constants.dataStoreFileName
    }
}


@Composable
internal actual fun VideoPlayer(modifier: Modifier, url: String?, thumbnail: String?) {
    val videoId = splitLinkForVideoId(url.toString())
    DesktopWebView(modifier, "https://www.youtube.com/embed/$videoId")
}

fun splitLinkForVideoId(
    url: String?,
): String {
    return url?.substringAfter("v=").toString()
}

private fun openYouTubeVideo(videoUrl: String) {
    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        Desktop.getDesktop().browse(URI(videoUrl))
    }
}


fun splitLinkForShotsVideoId(url: String?): String {
    return url!!.split("v=").get(1)
}


actual class VideoDownloader {
    actual suspend fun downloadVideo(url: String, onProgress: (Float, String) -> Unit): String {
        return withContext(Dispatchers.IO) {
            try {
                val userHome = System.getProperty("user.home")
                val downloadDir = Paths.get(userHome, "Desktop").toString()
                val destination = "$downloadDir/%(title)s.%(ext)s"
                val command =
                    listOf("C:\\Program Files\\yt-dlp\\yt-dlp.exe", "-o", destination, url)
                val processBuilder = ProcessBuilder(command)
                val process = processBuilder.start()
                val reader = BufferedReader(InputStreamReader(process.inputStream))
                val output = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    output.append(line).append("\n")
                    onProgress(0.5f, line ?: "")
                }
                process.waitFor(10, TimeUnit.MINUTES)
                if (process.exitValue() != 0) {
                    throw Exception("Error downloading video: ${output.toString()}")
                }
                output.toString()
            } catch (e: Exception) {
                e.printStackTrace()
                "Error: ${e.message}"
            }
        }
    }
}

actual fun HttpClientConfig<*>.setupHttpCache() {
    install(HttpCache) {
        val cacheDir = Paths.get(System.getProperty("user.home"), ".cache", "myAppCache").toFile()
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
        publicStorage(FileStorage(cacheDir))
    }
}