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
    suspend fun insert(foodOrder: FoodOrder): Long

    @Update
    suspend fun update(foodOrder: FoodOrder)

    @Delete
    suspend fun delete(foodOrder: FoodOrder)

    @Query("Delete from food_order")
    suspend fun deleteAll()

    // https://medium.com/@sdevpremthakur/how-to-reset-room-db-completely-including-primary-keys-android-6382f00df87b
    @Query("DELETE FROM sqlite_sequence WHERE name = 'food_order'")
    suspend fun deletePrimaryKeyIndex()

    @Query("SELECT * from food_order WHERE orderId = :orderId")
    fun getFoodOrder(orderId: Int): Flow<FoodOrder>

    @Query("SELECT * from food_order ORDER BY orderId ASC")
    fun getAllFoodOrders(): Flow<List<FoodOrder>>
}