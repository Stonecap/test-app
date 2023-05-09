package com.stonecap.coil_firebase

import android.webkit.MimeTypeMap
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.decode.DataSource
import coil.decode.ImageSource
import coil.disk.DiskCache
import coil.fetch.FetchResult
import coil.fetch.Fetcher
import coil.fetch.SourceResult
import coil.request.Options
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import okhttp3.internal.closeQuietly
import okio.buffer
import okio.source
import java.io.InputStream
import kotlin.Exception

@OptIn(ExperimentalCoilApi::class)
class FirebaseStorageFetcher(
    private val reference: StorageReference,
    private val options: Options,
    private val diskCache: DiskCache?,
) : Fetcher {
    override suspend fun fetch(): FetchResult {

        val mimeType: String? =
            getMimeType(reference.path) ?: reference.metadata.await().contentType

        var snapshot = readFromDiskCache()

        if (diskCache != null && snapshot != null) {
            return SourceResult(
                source = ImageSource(
                    snapshot.data,
                    diskCache.fileSystem,
                    diskCacheKey,
                    snapshot,
                ),
                mimeType = mimeType,
                dataSource = DataSource.DISK,
            )
        }

        // Slow path: fetch the image from the network.
        val response = reference.stream.await()

        try {
            // Write the response to the disk cache then open a new snapshot.
            snapshot = writeToDiskCache(response.stream)

            return SourceResult(
                dataSource = DataSource.NETWORK,
                source = snapshot!!.toImageSource(),
                mimeType = mimeType,
            )
        } catch (e: Exception) {
            snapshot?.closeQuietly()
            throw e
        }
    }

    private fun readFromDiskCache(): DiskCache.Snapshot? {
        return if (options.diskCachePolicy.readEnabled) {
            diskCache?.get(diskCacheKey)
        } else {
            null
        }
    }

    private fun writeToDiskCache(inputStream: InputStream): DiskCache.Snapshot? {
        // Open a new editor.
        val editor = diskCache?.edit(diskCacheKey) ?: return null

        try {
            fileSystem.write(editor.data) {
                inputStream.source().buffer().readAll(this)
            }
            return editor.commitAndGet()

        } catch (e: Exception) {
            try {
                editor.abort()
            } catch (_: Exception) {
            }
            throw e
        }
    }

    private fun getMimeType(path: String): String? {
        val extension: String = path.substringAfterLast('.', missingDelimiterValue = "")
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }

    private fun DiskCache.Snapshot.toImageSource(): ImageSource {
        return ImageSource(data, fileSystem, diskCacheKey, this)
    }

    private val diskCacheKey get() = options.diskCacheKey ?: reference.path
    private val fileSystem get() = diskCache!!.fileSystem


    class Factory : Fetcher.Factory<StorageReference> {
        override fun create(
            data: StorageReference,
            options: Options,
            imageLoader: ImageLoader,
        ): Fetcher {
            return FirebaseStorageFetcher(
                reference = data,
                options = options,
                diskCache = imageLoader.diskCache,
            )
        }
    }
}
