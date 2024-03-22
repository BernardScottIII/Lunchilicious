package com.scottb4.lunchilicious

data class MenuItem(
    val id: Int,
    val type: String,
    val name: String,
    val description: String,
    val price: Double
)