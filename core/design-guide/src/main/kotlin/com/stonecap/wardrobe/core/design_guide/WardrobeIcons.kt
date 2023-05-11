package com.stonecap.wardrobe.core.design_guide

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Abc
import androidx.compose.material.icons.twotone.Comment
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Recommend
import androidx.compose.ui.graphics.vector.ImageVector

object WardrobeIcons {
    val Home = Icons.TwoTone.Home
    val closet = Icons.TwoTone.Abc
    val community = Icons.TwoTone.Comment
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
