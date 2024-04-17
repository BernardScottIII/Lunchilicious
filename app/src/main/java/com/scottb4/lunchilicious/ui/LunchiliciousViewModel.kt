package com.scottb4.lunchilicious.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scottb4.lunchilicious.LunchiliciousApplication
import com.scottb4.lunchilicious.data.FoodOrder
import com.scottb4.lunchilicious.data.LineItem
import com.scottb4.lunchilicious.data.MenuItem
import com.scottb4.lunchilicious.domain.LunchiliciousRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LunchiliciousViewModel (
    private val lunchiliciousRepository: LunchiliciousRepository
): ViewModel() {

    private var _selectedMenuItems = mutableStateListOf<MenuItem>()
    private var _detailsValueList = mutableStateListOf<Boolean>()

    fun getLength() = runBlocking {
        val resultDeferred = async { lunchiliciousRepository.getNumMenuItems() }
        return@runBlocking resultDeferred.await()
    }

    private val length = getLength()

    fun selectMenuItem(menuItem: MenuItem) {
        _selectedMenuItems.add(menuItem)
    }

    fun removeMenuItem(menuItem: MenuItem) {
        _selectedMenuItems.remove(menuItem)
    }

    private fun createDetailsValueList(): SnapshotStateList<Boolean> {
        for (i in 0..length) {
            _detailsValueList.add(false)
        }

        return _detailsValueList
    }

    val selectedMenuItems = _selectedMenuItems
    val detailsValueList = createDetailsValueList()
    var tempMenuItemType by mutableStateOf("")
    var tempMenuItemName by mutableStateOf("")
    var tempMenuItemDesc by mutableStateOf("")
    var tempMenuItemPrice by mutableStateOf("")

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