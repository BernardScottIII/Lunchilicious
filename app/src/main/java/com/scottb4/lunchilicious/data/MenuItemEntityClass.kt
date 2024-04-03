package com.scottb4.lunchilicious.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_item")
data class MenuItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String,
    val name: String,
    val description: String,
    val unit_price: Double
)