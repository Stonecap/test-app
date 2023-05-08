package com.stonecap.wardrobe.core.model

import kotlinx.datetime.Instant

data class Posting<out T>(
    val id: String,
    val authorId: String,
    val timeCreated: Instant,
    val likes: UInt,
    val hashTags: Set<HashTag>,
    val content: T,
)
