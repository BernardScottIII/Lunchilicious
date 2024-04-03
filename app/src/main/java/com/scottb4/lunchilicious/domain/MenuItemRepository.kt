package com.scottb4.lunchilicious.domain

import com.scottb4.lunchilicious.data.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuItemRepository {
    fun getAllMenuItemsStream(): Flow<List<MenuItem>>
    fun getMenuItemStream(id: Int): Flow<MenuItem?>
    suspend fun insertMenuItem(menuItem: MenuItem)
    suspend fun deleteMenuItem(menuItem: MenuItem)
    suspend fun updateMenuItem(menuItem: MenuItem)
}