package com.scottb4.lunchilicious.ui

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

sealed interface LunchiliciousUiState {
    data class Success(val menuItems: List<MenuItem>) : LunchiliciousUiState
    object Error : LunchiliciousUiState
    object Loading : LunchiliciousUiState
}

sealed interface FoodOrderUiState {
    data class Success(
        val foodOrders: List<FoodOrder>) : FoodOrderUiState
    object Error : FoodOrderUiState
    object Loading : FoodOrderUiState
}

sealed interface OrderItemUiState {
    data class Success(val lineItems: List<LineItem>): OrderItemUiState
    object Error: OrderItemUiState
    object Loading: OrderItemUiState
}

class LunchiliciousViewModel (
    private val lunchiliciousRepo: LunchiliciousRepo
): ViewModel() {

    private var _selectedMenuItems = mutableStateListOf<MenuItem>()
    private var _showingMenuItemDetails = mutableStateListOf<MenuItem>()
    private var _tempMenuItemType = mutableStateOf("")
    private var _tempMenuItemName = mutableStateOf("")
    private var _tempMenuItemDesc = mutableStateOf("")
    private var _tempMenuItemPrice = mutableStateOf("")
    private var _validateTempMenuItemInput = mutableStateOf(false)

    var lunchiliciousUiState: LunchiliciousUiState by
    mutableStateOf(LunchiliciousUiState.Loading)
        private set

    var foodOrderUiState: FoodOrderUiState by
    mutableStateOf(FoodOrderUiState.Loading)
        private set

    var orderItemUiState: OrderItemUiState by
    mutableStateOf(OrderItemUiState.Loading)
        private set

    init {
        getMenuItems()
        getAllOrders()
    }

    fun selectMenuItem(menuItem: MenuItem) {
        _selectedMenuItems.add(menuItem)
    }

    fun removeMenuItem(menuItem: MenuItem) {
        _selectedMenuItems.remove(menuItem)
    }

    fun showMenuItemDetails(menuItem: MenuItem) {
        _showingMenuItemDetails.add(menuItem)
    }

    fun hideMenuItemDetails(menuItem: MenuItem) {
        _showingMenuItemDetails.remove(menuItem)
    }

    fun setTempMenuItemValidation(status: Boolean) {
        _validateTempMenuItemInput.value = status
    }

    val selectedMenuItems = _selectedMenuItems
    val showingMenuItemDetails = _showingMenuItemDetails
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
        private const val USERID = "scottb4"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LunchiliciousApplication)
                val lunchiliciousRepository = application.lunchiliciousRepo
                LunchiliciousViewModel(lunchiliciousRepo = lunchiliciousRepository)
            }
        }
    }

    suspend fun insertAllLineItems(
        menuItems: MutableMap<Long, Long>,
        orderId: String
    ) {
        val localLineItems:MutableList<LineItem> = mutableListOf()
        var idx = 0L // Work-around for being unable to auto-generate part of a composite primary key
        menuItems.forEach { (menuItemId, quantity) ->
            val newLineItem = LineItem(
                lineNum = (idx + 1L),
                orderId = orderId,
                itemId = menuItemId,
                quantity = quantity
            )
            lunchiliciousRepo.insertLineItem(newLineItem)
            localLineItems.add(newLineItem)
            idx += 1
        }
        lunchiliciousRepo.addLineItems(localLineItems)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createNewFoodOrder(totalCost: Double): String {
        val newFoodOrder = FoodOrder(
            orderId = USERID + "-" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MM-dd-HH-mm-ss")),
            orderDate = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            totalCost = totalCost
        )
        lunchiliciousRepo.addFoodOrder(newFoodOrder)
        lunchiliciousRepo.insertFoodOrder(newFoodOrder)
        return newFoodOrder.orderId
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

    fun getMenuItems() {
        viewModelScope.launch {
            lunchiliciousUiState = LunchiliciousUiState.Loading
            lunchiliciousUiState = try {
                LunchiliciousUiState.Success(lunchiliciousRepo.getMenuItems())
            } catch (e: IOException) {
                LunchiliciousUiState.Error
            } catch (e: HttpException) {
                Log.i("HTTP", e.toString())
                LunchiliciousUiState.Error
            }
            LunchiliciousUiState.Success(lunchiliciousRepo.getMenuItems()).menuItems.forEach {
                insertMenuItem(
                    MenuItem(
                        id = it.id,
                        type = it.type,
                        name = it.name,
                        description = it.description,
                        unitPrice = it.unitPrice
                    )
                )
            }
        }
    }

    fun getAllOrders() {
        viewModelScope.launch {
            foodOrderUiState = FoodOrderUiState.Loading
            foodOrderUiState = try {
                FoodOrderUiState.Success(lunchiliciousRepo.getAllOrders())
            } catch (e: IOException) {
                FoodOrderUiState.Error
            } catch (e: HttpException) {
                FoodOrderUiState.Error
            }
            FoodOrderUiState.Success(lunchiliciousRepo.getAllOrders()).foodOrders.forEach {
                if(it.orderId.contains(USERID)) {
                    insertFoodOrder(
                        FoodOrder(
                            orderId = it.orderId,
                            orderDate = it.orderDate,
                            totalCost = it.totalCost
                        )
                    )
                }
            }
        }
    }

    fun getLineItemsByOrderId(orderId: String) {
        if(!orderId.contains(USERID)) {
            orderItemUiState = OrderItemUiState.Error
            return
        }
        viewModelScope.launch {
            orderItemUiState = OrderItemUiState.Loading
            orderItemUiState = try {
                OrderItemUiState.Success(lunchiliciousRepo.getLineItemsByOrderId(orderId))
            } catch (e: IOException) {
                OrderItemUiState.Error
            } catch (e: HttpException) {
                OrderItemUiState.Error
            }
        }
    }

    fun addMenuItem(menuItem: MenuItem) {
        viewModelScope.launch {
            val newMenuItem = lunchiliciousRepo.addMenuItem(menuItem)
            val newId = newMenuItem.id
            lunchiliciousRepo.insertMenuItem(
                MenuItem(
                    id = newId,
                    type = newMenuItem.type,
                    name = newMenuItem.name,
                    description = newMenuItem.description,
                    unitPrice = newMenuItem.unitPrice
                )
            )
        }
    }

    fun getMenuItemStream(itemId: Long): Flow<MenuItem> =
        lunchiliciousRepo.getMenuItemStream(itemId)
}