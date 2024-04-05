package com.scottb4.lunchilicious.data

import com.scottb4.lunchilicious.domain.LineItemRepository
import kotlinx.coroutines.flow.Flow

class LineItemRepositoryImplementation(private val lineItemDb: LunchiliciousDatabase): LineItemRepository {
    override fun getAllLineItemsStream(): Flow<List<LineItem>> =
        lineItemDb.lineItemDao().getAllLineItems()

    override fun getLineItemStream(id: Int): Flow<LineItem?> =
        lineItemDb.lineItemDao().getLineItem(id)

    override suspend fun insertLineItem(lineItem: LineItem): Long =
        lineItemDb.lineItemDao().insert(lineItem)

    override suspend fun deleteLineItem(lineItem: LineItem) =
        lineItemDb.lineItemDao().delete(lineItem)

    override suspend fun updateLineItem(lineItem: LineItem) =
        lineItemDb.lineItemDao().update(lineItem)
}