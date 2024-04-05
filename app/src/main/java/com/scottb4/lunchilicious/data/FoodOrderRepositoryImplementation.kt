package com.scottb4.lunchilicious.data

import com.scottb4.lunchilicious.domain.FoodOrderRepository
import kotlinx.coroutines.flow.Flow

class FoodOrderRepositoryImplementation (private val foodOrderDb: LunchiliciousDatabase): FoodOrderRepository {
    override fun getAllFoodOrdersStream(): Flow<List<FoodOrder>> =
        foodOrderDb.foodOrderDao().getAllFoodOrders()

    override fun getFoodOrderStream(id: Int): Flow<FoodOrder?> =
        foodOrderDb.foodOrderDao().getFoodOrder(id)

    override suspend fun insertFoodOrder(foodOrder: FoodOrder): Long =
        foodOrderDb.foodOrderDao().insert(foodOrder)

    override suspend fun deleteFoodOrder(foodOrder: FoodOrder) =
        foodOrderDb.foodOrderDao().delete(foodOrder)

    override suspend fun updateFoodOrder(foodOrder: FoodOrder) =
        foodOrderDb.foodOrderDao().update(foodOrder)
}