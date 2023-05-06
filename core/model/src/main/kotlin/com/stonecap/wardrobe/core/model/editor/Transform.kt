package com.stonecap.wardrobe.core.model.editor


data class Transform(
    val translate: Pair<Int, Int>,
    val scale: Float = 1f,
    val rotation: Float = 0f
)
