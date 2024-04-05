package com.scottb4.lunchilicious.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scottb4.lunchilicious.LunchiliciousApplication
import com.scottb4.lunchilicious.data.LineItem
import com.scottb4.lunchilicious.data.MenuItem
import com.scottb4.lunchilicious.domain.LineItemRepository
import kotlinx.coroutines.flow.Flow

class LineItemViewModel (
    private val lineItemRepository: LineItemRepository
): ViewModel() {

    suspend fun insertAllLineItems(menuItems: MutableList<MenuItem>,
                           o_id: Long) {
        // Work-around for being unable to auto-generate part of a composite primary key
        menuItems.forEachIndexed { idx, menuItem ->
            lineItemRepository.insertLineItem(
                LineItem(
                    line_no = (idx + 1).toLong(),
                    o_id = o_id,
                    item_id = menuItem.id
                )
            )
        }
    }

    fun getAllLineItems(): Flow<List<LineItem>> =
        lineItemRepository.getAllLineItemsStream()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                            LunchiliciousApplication).lineItemRepository
                LineItemViewModel(
                    lineItemRepository = myRepository
                )
            }
        }
    }
}