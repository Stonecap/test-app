package com.stonecap.wardrobe.core.model.editor

data class CompositeImage(
    val components: Set<String>,
    val transform: Map<String, Transform>,
    val dimension: Pair<Int, Int>,
)
