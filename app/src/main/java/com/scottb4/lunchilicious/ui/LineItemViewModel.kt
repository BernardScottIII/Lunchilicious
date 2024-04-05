package com.scottb4.lunchilicious.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scottb4.lunchilicious.LunchiliciousApplication
import com.scottb4.lunchilicious.data.LineItem
import com.scottb4.lunchilicious.data.MenuItem
import com.scottb4.lunchilicious.domain.LineItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LineItemViewModel (
    private val lineItemRepository: LineItemRepository
): ViewModel() {
//    fun createLineItems() {
//        viewModelScope.launch {
//            (0..3).forEach { _ ->
//                val lineItem = LineItem(
//                    line_no = (0..100).random(),
//                    o_id = (0..100).random(),
//                    item_id = (0..100).random()
//                )
//                lineItemRepository.insertLineItem(lineItem)
//            }
//        }
//    }

    fun putAllLineItems(menuItems: Array<MenuItem>,
                        o_id: Long) {
        viewModelScope.launch {
            menuItems.forEach { menuItem ->
                lineItemRepository.insertLineItem(
                    LineItem(
                        o_id = o_id,
                        item_id = menuItem.id
                    )
                )
            }
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