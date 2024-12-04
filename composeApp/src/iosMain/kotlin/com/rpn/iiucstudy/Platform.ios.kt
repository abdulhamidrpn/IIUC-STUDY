package com.rpn.iiucstudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import com.rpn.iiucstudy.presentation.utils.Type
import platform.UIKit.UIDevice
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.cache.HttpCache
import kotlinx.cinterop.CValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resumeWithException

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()





actual fun getType(): Type {
    return Type.Mobile
}

actual fun getRandomId(): String {
    return NSUUIID.UUIDString()
}

actual fun shareLink(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}


actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producerPath = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/${Constants.dataStoreFileName}"
        })
}

@Composable
internal actual fun VideoPlayer(modifier: Modifier, url: String?, thumbnail: String?) {

    val player = remember { NSURL.URLWithString(url.toString())?.let { AVPlayer(uRL = it) } }
    val playerLayer = remember { AVPlayerLayer() }
    val avPlayerViewController = remember { AVPlayerViewController() }
    avPlayerViewController.player = player
    avPlayerViewController.showsPlaybackControls = true

    playerLayer.player = player
    UIKitView(
        factory = {
            val playerContainer = UIView()
            playerContainer.addSubview(avPlayerViewController.view)
            playerContainer
        },
        onResize = { view: UIView, rect: CValue<CGRect> ->
            CATransaction.begin()
            CATransaction.setValue(true, kCATransactionDisableActions)
            view.layer.setFrame(rect)
            playerLayer.setFrame(rect)
            avPlayerViewController.view.layer.frame = rect
            CATransaction.commit()
        },
        update = { view ->
            player!!.play()
            avPlayerViewController.player!!.play()
        },
        modifier = modifier
    )
}


actual class VideoDownloader {
    actual suspend fun downloadVideo(url: String, onProgress: (Float, String) -> Unit): String {
        return withContext(Dispatchers.Main) {
            try {
                val nsUrl = NSURL.URLWithString(url)
                val request = NSURLRequest.requestWithURL(nsUrl!!)
                val config = NSURLSessionConfiguration.defaultSessionConfiguration()
                val session = NSURLSession.sessionWithConfiguration(config, null, null)
                var downloadOutput = ""

                session.dataTaskWithRequest(request) { data, response, error ->
                    if (error != null) {
                        onProgress(1.0f, "Error: ${error.localizedDescription}")
                        downloadOutput = "Error: ${error.localizedDescription}"
                    } else {
                        val downloadDir = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true).first() as String
                        val filePath = downloadDir + "/downloaded_video.mp4"
                        data?.writeToFile(filePath, true)
                        onProgress(1.0f, "Download complete: $filePath")
                        downloadOutput = filePath
                    }
                }.resume()

                downloadOutput
            } catch (e: Exception) {
                e.printStackTrace()
                "Error: ${e.message}"
            }
        }
    }
}

actual fun HttpClientConfig<*>.setupHttpCache() {
    install(HttpCache) {
        val paths = NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.CachesDirectory, NSUserDomainMask, true)
        val cacheDir = paths.first() as String
        val fileStorage = FileStorage(NSFileManager.defaultManager.URLForDirectory(NSSearchPathDirectory.CachesDirectory, NSUserDomainMask, true, null, null)!!)
        publicStorage(fileStorage)
    }
}