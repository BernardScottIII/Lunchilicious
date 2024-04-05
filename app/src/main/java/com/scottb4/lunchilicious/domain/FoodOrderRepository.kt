package com.scottb4.lunchilicious.domain

import com.scottb4.lunchilicious.data.FoodOrder
import kotlinx.coroutines.flow.Flow

interface FoodOrderRepository {
    fun getAllFoodOrdersStream(): Flow<List<FoodOrder>>
    fun getFoodOrderStream(id: Int): Flow<FoodOrder?>
    suspend fun insertFoodOrder(foodOrder: FoodOrder): Long
    suspend fun deleteFoodOrder(foodOrder: FoodOrder)
    suspend fun updateFoodOrder(foodOrder: FoodOrder)
}