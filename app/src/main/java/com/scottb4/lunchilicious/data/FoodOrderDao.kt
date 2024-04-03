package com.scottb4.lunchilicious.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodOrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foodOrder: FoodOrder)

    @Update
    suspend fun update(foodOrder: FoodOrder)

    @Delete
    suspend fun delete(foodOrder: FoodOrder)

    @Query("Delete from food_order")
    suspend fun deleteAll()

    @Query("SELECT * from food_order WHERE id = :id")
    fun getFoodOrder(id: Int): Flow<FoodOrder>

    @Query("SELECT * from food_order ORDER BY id ASC")
    fun getAllFoodOrders(): Flow<List<FoodOrder>>
}