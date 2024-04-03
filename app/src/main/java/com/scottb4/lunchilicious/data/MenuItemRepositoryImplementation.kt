package com.scottb4.lunchilicious.data

import com.scottb4.lunchilicious.domain.MenuItemRepository
import kotlinx.coroutines.flow.Flow

class MenuItemRepositoryImplementation (private val menuItemDb: LunchiliciousDatabase): MenuItemRepository {
    override fun getAllMenuItemsStream(): Flow<List<MenuItem>> =
        menuItemDb.menuItemDao().getAllMenuItems()

    override fun getMenuItemStream(id: Int): Flow<MenuItem?> =
        menuItemDb.menuItemDao().getMenuItem(id)

    override suspend fun insertMenuItem(menuItem: MenuItem): Long =
        menuItemDb.menuItemDao().insert(menuItem)

    override suspend fun deleteMenuItem(menuItem: MenuItem) =
        menuItemDb.menuItemDao().delete(menuItem)

    override suspend fun updateMenuItem(menuItem: MenuItem) =
        menuItemDb.menuItemDao().update(menuItem)
}