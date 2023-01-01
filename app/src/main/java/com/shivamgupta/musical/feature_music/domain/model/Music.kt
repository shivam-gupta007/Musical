package com.shivamgupta.musical.feature_music.domain.model

data class Music(
    val id: Long,
    val title: String,
    val artist: String,
    val duration: String,
    val dateAdded: String
)
