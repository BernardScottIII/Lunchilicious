package com.scottb4.lunchilicious.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scottb4.lunchilicious.LunchiliciousApplication
import com.scottb4.lunchilicious.data.FoodOrder
import com.scottb4.lunchilicious.data.LineItem
import com.scottb4.lunchilicious.data.LunchiliciousDatabase
import com.scottb4.lunchilicious.data.MenuItem
import com.scottb4.lunchilicious.domain.LunchiliciousRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LunchiliciousViewModel (
    private val lunchiliciousRepository: LunchiliciousRepository
): ViewModel() {

    private var _checkboxValueList = mutableStateListOf<Boolean>()
    private var _detailsValueList = mutableStateListOf<Boolean>()
    private val length = LunchiliciousDatabase.getNumberOfItems()

    private fun createCheckboxValueList(): SnapshotStateList<Boolean> {
        for (i in 0..length) {
            _checkboxValueList.add(false)
        }

        return _checkboxValueList
    }

    private fun createDetailsValueList(): SnapshotStateList<Boolean> {
        for (i in 0..length) {
            _detailsValueList.add(false)
        }

        return _detailsValueList
    }

    val checkboxValueList = createCheckboxValueList()
    val detailsValueList = createDetailsValueList()

    fun getAllMenuItems(): Flow<List<MenuItem>> {
        return lunchiliciousRepository.getAllMenuItemsStream()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                            LunchiliciousApplication).lunchiliciousRepository
                LunchiliciousViewModel(lunchiliciousRepository = myRepository)
            }
        }
    }

    suspend fun insertAllLineItems(menuItems: MutableList<MenuItem>,
                                   o_id: Long) {
        // Work-around for being unable to auto-generate part of a composite primary key
        menuItems.forEachIndexed { idx, menuItem ->
            lunchiliciousRepository.insertLineItem(
                LineItem(
                    line_no = (idx + 1).toLong(),
                    o_id = o_id,
                    item_id = menuItem.id
                )
            )
        }
    }

    fun getAllLineItems(): Flow<List<LineItem>> =
        lunchiliciousRepository.getAllLineItemsStream()

    suspend fun createNewFoodOrder(totalCost: Double): Long {
        return lunchiliciousRepository.insertFoodOrder(
            FoodOrder(total_cost = totalCost)
        )
    }

    fun getAllFoodOrders(): Flow<List<FoodOrder>> {
        return lunchiliciousRepository.getAllFoodOrdersStream()
    }

    fun insertFoodOrder(foodOrder: FoodOrder) {
        viewModelScope.launch {
            lunchiliciousRepository.insertFoodOrder(foodOrder)
        }
    }
}