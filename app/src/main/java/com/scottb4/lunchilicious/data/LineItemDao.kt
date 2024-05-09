package com.scottb4.lunchilicious.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LineItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lineItem: LineItem): Long

    @Update
    suspend fun update(lineItem: LineItem)

    @Delete
    suspend fun delete(lineItem: LineItem)

    @Query("Delete from line_item")
    suspend fun deleteAll()

    // https://medium.com/@sdevpremthakur/how-to-reset-room-db-completely-including-primary-keys-android-6382f00df87b
    @Query("DELETE FROM sqlite_sequence WHERE name = 'line_item'")
    suspend fun deletePrimaryKeyIndex()

    @Query("SELECT * from line_item WHERE lineNo = :lineNo")
    fun getLineItem(lineNo: Int): Flow<LineItem>

    @Query("SELECT * from line_item ORDER BY lineNo ASC")
    fun getAllLineItems(): Flow<List<LineItem>>
}