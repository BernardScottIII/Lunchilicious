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
    suspend fun insert(lineItem: LineItem)

    @Update
    suspend fun update(lineItem: LineItem)

    @Delete
    suspend fun delete(lineItem: LineItem)

    @Query("Delete from line_item")
    suspend fun deleteAll()

    @Query("SELECT * from line_item WHERE line_no = :line_no")
    fun getLineItem(line_no: Int): Flow<LineItem>

    @Query("SELECT * from line_item ORDER BY line_no ASC")
    fun getAllLineItems(): Flow<List<LineItem>>
}