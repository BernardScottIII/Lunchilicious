package com.scottb4.lunchilicious.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "line_item",
    foreignKeys = [
        ForeignKey(
            entity = FoodOrder::class,
            parentColumns = ["id"],
            childColumns = ["o_id"]
        ),
        ForeignKey(
            entity = MenuItem::class,
            parentColumns = ["id"],
            childColumns = ["item_id"]
        )
    ]
)
data class LineItem (
    @PrimaryKey(autoGenerate = true)
    val line_no: Int = 0,
    @ColumnInfo(name = "o_id", index = true)
    val o_id: Int,
    @ColumnInfo(name = "item_id", index = true)
    val item_id: Int,
)