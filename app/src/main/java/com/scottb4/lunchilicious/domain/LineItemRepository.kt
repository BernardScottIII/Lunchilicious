package com.scottb4.lunchilicious.domain

import com.scottb4.lunchilicious.data.LineItem
import kotlinx.coroutines.flow.Flow

interface LineItemRepository {
    fun getAllLineItemsStream(): Flow<List<LineItem>>
    fun getLineItemStream(id: Int): Flow<LineItem?>
    suspend fun insertLineItem(lineItem: LineItem)
    suspend fun deleteLineItem(lineItem: LineItem)
    suspend fun updateLineItem(lineItem: LineItem)
}