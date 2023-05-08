package com.stonecap.wardrobe.core.model.editor

data class CompositeImage(
    val imageRefs: Set<String>,
    val transform: Map<String, Transform>,
)
