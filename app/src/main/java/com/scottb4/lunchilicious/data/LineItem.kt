package com.scottb4.lunchilicious.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "line_item",
    primaryKeys = [
        "lineNum", "orderId"
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
    @ColumnInfo(name = "lineNum")
    val lineNum: Long = 0,
    @ColumnInfo(name = "orderId", index = true)
    val orderId: String,
    @ColumnInfo(name = "itemId", index = true)
    val itemId: Long,
    val quantity: Long
)