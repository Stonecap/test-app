package com.stonecap.wardrobe.core.network

import com.stonecap.wardrobe.core.network.model.SegregatedClothes

interface RembgDataSource {
    suspend fun segregateClothesPicture(image: ByteArray): List<SegregatedClothes>
}
