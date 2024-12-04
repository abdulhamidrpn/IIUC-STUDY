package com.rpn.iiucstudy

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.rpn.iiucstudy.data.utils.Constants
import com.rpn.iiucstudy.presentation.utils.Type
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.UUID
import java.util.concurrent.TimeUnit
import android.app.Application
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.rpn.iiucstudy.ui.YoutubeVideoPlayer
import org.koin.mp.KoinPlatform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


actual fun getType(): Type {
    return Type.Mobile
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share Link")
    ContextUtils.context.startActivity(shareIntent)

}

actual fun dataStorePreferences(): DataStore<Preferences> {
    val context = ContextUtils.context
    return AppSettings.getDataStore(
        producerPath = {
            context.filesDir.resolve(Constants.dataStoreFileName).absolutePath
        }
    )
}


@RequiresApi(Build.VERSION_CODES.S)
@Composable
internal actual fun VideoPlayer(modifier: Modifier, url: String?, thumbnail: String?) {
    YoutubeVideoPlayer(youtubeURL = url)
}


actual class VideoDownloader {
    actual suspend fun downloadVideo(url: String, onProgress: (Float, String) -> Unit): String {
        return withContext(Dispatchers.IO) {
            "Downloading video from URL: $url"
            /*try {
                val context = KoinPlatform.getKoin().get<Application>()
                val ytDlpPath = copyExecutableToInternalStorage(context)

                val downloadDir = context.getExternalFilesDir(null)?.absolutePath
                    ?: throw IOException("Failed to get external files directory")
                val destination = "$downloadDir/%(title)s.%(ext)s"
                val command = listOf(ytDlpPath, "-o", destination, url)

                Log.d("VideoDownloader", "Command to run: $command")

                val processBuilder = ProcessBuilder(command)
                processBuilder.directory(context.filesDir)
                processBuilder.redirectErrorStream(true)

                val process = processBuilder.start()
                val reader = BufferedReader(InputStreamReader(process.inputStream))
                val output = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    output.append(line).append("\n")
                    onProgress(0.5f, line ?: "")
                    Log.d("VideoDownloader", "Output: $line")
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    process.waitFor(10, TimeUnit.MINUTES)
                }

                val exitValue = process.exitValue()
                Log.d("VideoDownloader", "Process exit value: $exitValue")

                if (exitValue != 0) {
                    throw Exception("Error downloading video. Exit code: $exitValue")
                }

                output.toString()
            }
            catch (e: Exception) {
                e.printStackTrace()
                Log.e("VideoDownloader", "Error: ${e.message}")
                "Error: ${e.message}"
            }*/
        }
    }
}

fun copyExecutableToInternalStorage(context: Context): String {
    val externalFilePath = "/storage/emulated/0/Download/yt-dlp.exe"
    val externalFile = File(externalFilePath)
    if (!externalFile.exists()) {
        throw IOException("yt-dlp.exe not found in external storage")
    }

    val internalFile = File(context.filesDir, "yt-dlp.exe")
    Log.d("VideoDownloader", "Copying yt-dlp.exe to ${internalFile.absolutePath}")

    externalFile.inputStream().use { input ->
        internalFile.outputStream().use { output ->
            input.copyTo(output)
        }
    }

    try {
        Log.d("VideoDownloader", "Setting executable permissions")
        Runtime.getRuntime().exec("chmod 755 ${internalFile.absolutePath}").waitFor()
    } catch (e: IOException) {
        Log.e("VideoDownloader", "Failed to set executable permission", e)
        throw IOException("Failed to set executable permission", e)
    }

    Log.d("VideoDownloader", "yt-dlp.exe copied and set as executable")
    return internalFile.absolutePath
}

actual fun HttpClientConfig<*>.setupHttpCache() {
    install(HttpCache) {
        val appContext = KoinPlatform.getKoin().get<Application>()
        val cacheDir = File(appContext.cacheDir, "myAppCache")
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
        publicStorage(FileStorage(cacheDir))
    }
}