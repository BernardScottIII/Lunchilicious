package com.scottb4.lunchilicious.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_order")
data class FoodOrder (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val total_cost: Double
)