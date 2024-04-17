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
    suspend fun insert(menuItem: MenuItem): Long

    @Update
    suspend fun update(menuItem: MenuItem)

    @Delete
    suspend fun delete(menuItem: MenuItem)

    @Query("Delete from menu_item")
    suspend fun deleteAll()

    // https://medium.com/@sdevpremthakur/how-to-reset-room-db-completely-including-primary-keys-android-6382f00df87b
    @Query("DELETE FROM sqlite_sequence WHERE name = 'menu_item'")
    suspend fun deletePrimaryKeyIndex()

    @Query("SELECT * from menu_item WHERE id = :id")
    fun getMenuItem(id: Int): Flow<MenuItem>

    @Query("SELECT * from menu_item ORDER BY name ASC")
    fun getAllMenuItems(): Flow<List<MenuItem>>

    @Query("SELECT COUNT(*) FROM 'menu_item'")
    suspend fun getNumMenuItems(): Int
}