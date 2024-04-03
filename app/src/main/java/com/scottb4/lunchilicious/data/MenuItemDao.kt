package com.scottb4.lunchilicious.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(menuItem: MenuItem)

    @Update
    suspend fun update(menuItem: MenuItem)

    @Delete
    suspend fun delete(menuItem: MenuItem)

    @Query("Delete from menu_item")
    suspend fun deleteAll()

    @Query("SELECT * from menu_item WHERE id = :id")
    fun getMenuItem(id: Int): Flow<MenuItem>

    @Query("SELECT * from menu_item ORDER BY name ASC")
    fun getAllMenuItems(): Flow<List<MenuItem>>
}