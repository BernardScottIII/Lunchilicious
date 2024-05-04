package com.scottb4.lunchilicious.domain

import com.scottb4.lunchilicious.data.FoodOrder
import com.scottb4.lunchilicious.data.LineItem
import com.scottb4.lunchilicious.data.MenuItem
import kotlinx.coroutines.flow.Flow

interface LunchiliciousRepo {
    fun getAllMenuItemsStream(): Flow<List<MenuItem>>
    fun getMenuItemStream(id: Int): Flow<MenuItem?>
    suspend fun insertMenuItem(menuItem: MenuItem): Long
    suspend fun deleteMenuItem(menuItem: MenuItem)
    suspend fun updateMenuItem(menuItem: MenuItem)
    fun getAllFoodOrdersStream(): Flow<List<FoodOrder>>
    fun getFoodOrderStream(id: Int): Flow<FoodOrder?>
    suspend fun insertFoodOrder(foodOrder: FoodOrder): Long
    suspend fun deleteFoodOrder(foodOrder: FoodOrder)
    suspend fun updateFoodOrder(foodOrder: FoodOrder)

    fun getAllLineItemsStream(): Flow<List<LineItem>>
    fun getLineItemStream(id: Int): Flow<LineItem?>
    suspend fun insertLineItem(lineItem: LineItem): Long
    suspend fun deleteLineItem(lineItem: LineItem)
    suspend fun updateLineItem(lineItem: LineItem)
    suspend fun getNumMenuItems(): Int

    suspend fun getMenuItems(): List<MenuItem>
}