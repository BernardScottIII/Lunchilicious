package com.scottb4.lunchilicious.ui

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import com.scottb4.lunchilicious.domain.LunchiliciousRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException

sealed interface LunchiliciousUiState {
    data class Success(val menuItems: List<MenuItem>) : LunchiliciousUiState
    object Error : LunchiliciousUiState
    object Loading : LunchiliciousUiState
}

class LunchiliciousViewModel (
    private val lunchiliciousRepository: LunchiliciousRepository
): ViewModel() {

    private var _selectedMenuItems = mutableStateListOf<MenuItem>()
    private var _detailsValueList = mutableStateListOf<Boolean>()
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

    fun getLength() = runBlocking {
        val resultDeferred = async { lunchiliciousRepository.getMenuItems().size }
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

    fun setTempMenuItemValidation(status: Boolean) {
        _validateTempMenuItemInput.value = status
    }

    val selectedMenuItems = _selectedMenuItems
    val detailsValueList = createDetailsValueList()
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
        return lunchiliciousRepository.getAllMenuItemsStream()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LunchiliciousApplication)
                val lunchiliciousRepository = application.lunchiliciousRepository
                LunchiliciousViewModel(lunchiliciousRepository = lunchiliciousRepository)
//                val myRepository =
//                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
//                            LunchiliciousApplication).lunchiliciousRepository
//                LunchiliciousViewModel(lunchiliciousRepository = myRepository)
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

    fun insertMenuItem(menuItem: MenuItem) {
        viewModelScope.launch {
            lunchiliciousRepository.insertMenuItem(menuItem)
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
                LunchiliciousUiState.Success(lunchiliciousRepository.getMenuItems())
            } catch (e: IOException) {
                Log.i("IO", e.toString())
                LunchiliciousUiState.Error
            } catch (e: HttpException) {
                Log.i("HTTP", e.toString())
                LunchiliciousUiState.Error
            }
        }
    }
}