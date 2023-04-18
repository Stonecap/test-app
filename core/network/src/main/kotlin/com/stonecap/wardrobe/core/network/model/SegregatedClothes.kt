package com.stonecap.wardrobe.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SegregatedClothes(
    val uri: String,
    val offset_x: Int,
    val offset_y: Int,
)
