package com.scottb4.lunchilicious.ui

import android.net.http.HttpException
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scottb4.lunchilicious.LunchiliciousApplication
import com.scottb4.lunchilicious.data.FoodOrder
import com.scottb4.lunchilicious.data.LineItem
import com.scottb4.lunchilicious.data.MenuItem
import com.scottb4.lunchilicious.domain.LunchiliciousRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface LunchiliciousUiState {
    data class Success(val menuItems: List<MenuItem>) : LunchiliciousUiState
    object Error : LunchiliciousUiState
    object Loading : LunchiliciousUiState
}

class LunchiliciousViewModel (
    private val lunchiliciousRepo: LunchiliciousRepo
): ViewModel() {

    private var _selectedMenuItems = mutableStateListOf<MenuItem>()
    private var _detailsValueList = mutableStateListOf<MenuItem>()
    private var _tempMenuItemType = mutableStateOf("")
    private var _tempMenuItemName = mutableStateOf("")
    private var _tempMenuItemDesc = mutableStateOf("")
    private var _tempMenuItemPrice = mutableStateOf("")
    private var _validateTempMenuItemInput = mutableStateOf(false)
    var lunchiliciousUiState: LunchiliciousUiState by
    mutableStateOf(LunchiliciousUiState.Loading)
        private set

    init {
        getMenuItems()
    }

    fun selectMenuItem(menuItem: MenuItem) {
        _selectedMenuItems.add(menuItem)
    }

    fun removeMenuItem(menuItem: MenuItem) {
        _selectedMenuItems.remove(menuItem)
    }

    fun showMenuItemDetails(menuItem: MenuItem) {
        _detailsValueList.add(menuItem)
    }

    fun hideMenuItemDetails(menuItem: MenuItem) {
        _detailsValueList.remove(menuItem)
    }

    fun setTempMenuItemValidation(status: Boolean) {
        _validateTempMenuItemInput.value = status
    }

    val selectedMenuItems = _selectedMenuItems
    val detailsValueList = _detailsValueList
    val tempMenuItemType by _tempMenuItemType
    val tempMenuItemName by _tempMenuItemName
    val tempMenuItemDesc by _tempMenuItemDesc
    val tempMenuItemPrice by _tempMenuItemPrice
    val validateTempMenuItemInput by _validateTempMenuItemInput

    fun updateTempMenuItemType(type: String) {
        _tempMenuItemType.value = type
    }
    fun updateTempMenuItemName(name: String) {
        _tempMenuItemName.value = name
    }
    fun updateTempMenuItemDesc(desc: String) {
        _tempMenuItemDesc.value = desc
    }
    fun updateTempMenuItemPrice(price: String) {
        _tempMenuItemPrice.value = price
    }

    fun validateTempMenuItemType(): Boolean = tempMenuItemType == "" && validateTempMenuItemInput
    fun validateTempMenuItemName(): Boolean = tempMenuItemName == "" && validateTempMenuItemInput
    fun validateTempMenuItemDesc(): Boolean = tempMenuItemDesc == "" && validateTempMenuItemInput
    fun validateTempMenuItemPrice(): Boolean =
        (tempMenuItemPrice.matches("[0-9]*[.]+[0-9]*[.]+[0-9]*".toRegex())
            || tempMenuItemPrice.indexOf("-") > -1
            || tempMenuItemPrice.indexOf(" ") > -1
            || tempMenuItemPrice == ""
            ) && validateTempMenuItemInput

    fun getAllMenuItems(): Flow<List<MenuItem>> {
        return lunchiliciousRepo.getAllMenuItemsStream()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LunchiliciousApplication)
                val lunchiliciousRepository = application.lunchiliciousRepo
                LunchiliciousViewModel(lunchiliciousRepo = lunchiliciousRepository)
//                val myRepository =
//                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
//                            LunchiliciousApplication).lunchiliciousRepo
//                LunchiliciousViewModel(lunchiliciousRepo = myRepository)
            }
        }
    }

    suspend fun insertAllLineItems(menuItems: MutableList<MenuItem>,
                                   o_id: Long) {
        // Work-around for being unable to auto-generate part of a composite primary key
        menuItems.forEachIndexed { idx, menuItem ->
            lunchiliciousRepo.insertLineItem(
                LineItem(
                    line_no = (idx + 1).toLong(),
                    o_id = o_id,
                    item_id = menuItem.id
                )
            )
        }
    }

    fun getAllLineItems(): Flow<List<LineItem>> =
        lunchiliciousRepo.getAllLineItemsStream()

    suspend fun createNewFoodOrder(totalCost: Double): Long {
        return lunchiliciousRepo.insertFoodOrder(
            FoodOrder(total_cost = totalCost)
        )
    }

    fun getAllFoodOrders(): Flow<List<FoodOrder>> {
        return lunchiliciousRepo.getAllFoodOrdersStream()
    }

    fun insertFoodOrder(foodOrder: FoodOrder) {
        viewModelScope.launch {
            lunchiliciousRepo.insertFoodOrder(foodOrder)
        }
    }

    fun insertMenuItem(menuItem: MenuItem) {
        viewModelScope.launch {
            lunchiliciousRepo.insertMenuItem(menuItem)
        }
    }

    fun clearTempMenuItemFields() {
        _tempMenuItemType.value = ""
        _tempMenuItemName.value = ""
        _tempMenuItemDesc.value = ""
        _tempMenuItemPrice.value = ""
    }

    private fun getMenuItems() {
        viewModelScope.launch {
            lunchiliciousUiState = LunchiliciousUiState.Loading
            lunchiliciousUiState = try {
                LunchiliciousUiState.Success(lunchiliciousRepo.getMenuItems())
            } catch (e: IOException) {
                Log.i("IO", e.toString())
                LunchiliciousUiState.Error
            } catch (e: HttpException) {
                Log.i("HTTP", e.toString())
                LunchiliciousUiState.Error
            }
            LunchiliciousUiState.Success(lunchiliciousRepo.getMenuItems()).menuItems.forEach {
                insertMenuItem(
                    MenuItem(
                        id = it.id + 10,
                        type = it.type,
                        name = it.name,
                        description = it.description,
                        unitPrice = it.unitPrice
                    )
                )
            }
        }
    }
}