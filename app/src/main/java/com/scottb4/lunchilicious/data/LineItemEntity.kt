package com.scottb4.lunchilicious.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "line_item",
    primaryKeys = [
        "line_no", "o_id"
    ],
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
    // TODO: Auto-Generate one attribute of this composite key
    @ColumnInfo(name = "line_no")
    val line_no: Long = 0,
    @ColumnInfo(name = "o_id", index = true)
    val o_id: Long,
    @ColumnInfo(name = "item_id", index = true)
    val item_id: Long,
)