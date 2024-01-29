package com.example.simpleapp.Model

data class Item(
    val id: Int,
    val name: String,
    val audioUrl: String,
    var isPlaying: Boolean = false,
)