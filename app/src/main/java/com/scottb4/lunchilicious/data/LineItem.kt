package com.scottb4.lunchilicious.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "line_item",
    primaryKeys = [
        "lineNo", "orderId"
    ],
    foreignKeys = [
        ForeignKey(
            entity = FoodOrder::class,
            parentColumns = ["orderId"],
            childColumns = ["orderId"]
        ),
        ForeignKey(
            entity = MenuItem::class,
            parentColumns = ["id"],
            childColumns = ["itemId"]
        )
    ]
)
data class LineItem(
    // TODO: Auto-Generate one attribute of this composite key
    @ColumnInfo(name = "lineNo")
    val lineNo: Long = 0,
    @ColumnInfo(name = "orderId", index = true)
    val orderId: String,
    @ColumnInfo(name = "itemId", index = true)
    val itemId: Long,
    val quantity: Long
)