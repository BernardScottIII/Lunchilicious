package com.scottb4.lunchilicious.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_order")
data class FoodOrder(
    @PrimaryKey val orderId: String = "",
    val orderDate: String,
    val totalCost: Double
)