package com.stonecap.wardrobe.core.model

import kotlinx.datetime.Instant

data class Posting<out T>(
    val id: String,
    val timeCreated: Instant,
    val tags: Set<Attribute>,
    val content: T,
)
