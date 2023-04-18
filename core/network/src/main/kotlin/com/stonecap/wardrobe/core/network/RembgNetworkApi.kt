package com.stonecap.wardrobe.core.network

import com.stonecap.wardrobe.core.network.model.SegregatedClothes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.resources.Resource
import javax.inject.Inject

@Resource("/seg_clothes")
class SegClothes()

class RembgNetworkApi @Inject constructor(
    private val client: HttpClient,
) : RembgDataSource {

    override suspend fun segregateClothesPicture(image: ByteArray): List<SegregatedClothes> {
        val response: HttpResponse = client.post(SegClothes()) {
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append(
                            "file",
                            image,
                            Headers.build {
                                append(HttpHeaders.ContentType, "image/png")
                                append(HttpHeaders.ContentDisposition, "filename=\"img.png\"")
                            },
                        )
                    },
                ),
            )
        }
        return response.body()
    }
}
