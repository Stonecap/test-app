package com.stonecap.wardrobe.core.model

import com.stonecap.wardrobe.core.model.editor.CompositeImage

data class Style(
    val name: String,
    val description: String,
    val compositeImage: CompositeImage,
)
