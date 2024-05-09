package com.scottb4.lunchilicious.data

import com.scottb4.lunchilicious.domain.LunchiliciousRepo
import com.scottb4.lunchilicious.network.LunchiliciousClient
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LunchiliciousRepoImpl (private val lunchiliciousDb: LunchiliciousDatabase): LunchiliciousRepo {
    override fun getAllMenuItemsStream(): Flow<List<MenuItem>> =
        lunchiliciousDb.menuItemDao().getAllMenuItems()

    override fun getMenuItemStream(id: Int): Flow<MenuItem?> =
        lunchiliciousDb.menuItemDao().getMenuItem(id)

    override suspend fun insertMenuItem(menuItem: MenuItem): Long =
        lunchiliciousDb.menuItemDao().insert(menuItem)

    override suspend fun deleteMenuItem(menuItem: MenuItem) =
        lunchiliciousDb.menuItemDao().delete(menuItem)

    override suspend fun updateMenuItem(menuItem: MenuItem) =
        lunchiliciousDb.menuItemDao().update(menuItem)

    override fun getAllFoodOrdersStream(): Flow<List<FoodOrder>> =
        lunchiliciousDb.foodOrderDao().getAllFoodOrders()

    override fun getFoodOrderStream(id: Int): Flow<FoodOrder?> =
        lunchiliciousDb.foodOrderDao().getFoodOrder(id)

    override suspend fun insertFoodOrder(foodOrder: FoodOrder): String =
        lunchiliciousDb.foodOrderDao().insert(foodOrder).toString()

    override suspend fun deleteFoodOrder(foodOrder: FoodOrder) =
        lunchiliciousDb.foodOrderDao().delete(foodOrder)

    override suspend fun updateFoodOrder(foodOrder: FoodOrder) =
        lunchiliciousDb.foodOrderDao().update(foodOrder)

    override fun getAllLineItemsStream(): Flow<List<LineItem>> =
        lunchiliciousDb.lineItemDao().getAllLineItems()

    override fun getLineItemStream(id: Int): Flow<LineItem?> =
        lunchiliciousDb.lineItemDao().getLineItem(id)

    override suspend fun insertLineItem(lineItem: LineItem): String =
        lunchiliciousDb.lineItemDao().insert(lineItem).toString()

    override suspend fun deleteLineItem(lineItem: LineItem) =
        lunchiliciousDb.lineItemDao().delete(lineItem)

    override suspend fun updateLineItem(lineItem: LineItem) =
        lunchiliciousDb.lineItemDao().update(lineItem)

    override suspend fun getNumMenuItems(): Int =
        lunchiliciousDb.menuItemDao().getNumMenuItems()

    val baseUrl = "http://aristotle.cs.scranton.edu/"
    private var lunchiliciousClient: LunchiliciousClient
    init {
// create a retrofit object
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
// create a Client object for MarsApiService
        lunchiliciousClient = retrofit.create(LunchiliciousClient::class.java)
    }
    override suspend fun getMenuItems(): List<MenuItem> =
        lunchiliciousClient.getMenuItems()

    override suspend fun getMenuItemById(id: Int): MenuItem =
        lunchiliciousClient.getMenuItemById(id)

    override suspend fun addMenuItem(menuItem: MenuItem): MenuItem =
        lunchiliciousClient.addMenuItem(menuItem)

    override suspend fun updateMenuItem(id: Int, menuItem: MenuItem): MenuItem =
        lunchiliciousClient.updateMenuItem(id, menuItem)

    override suspend fun deleteRemoteMenuItem(id: Int): MenuItem =
        lunchiliciousClient.deleteRemoteMenuItem(id)

    override suspend fun getOrderById(orderId: String): FoodOrder =
        lunchiliciousClient.getOrderById(orderId)

    override suspend fun addFoodOrder(foodOrder: FoodOrder): FoodOrder =
        lunchiliciousClient.addFoodOrder(foodOrder)

    override suspend fun getLineItemsByOrderId(orderId: String): List<LineItem> =
        lunchiliciousClient.getLineItemsByOrderId(orderId)

    override suspend fun addLineItems(lineItems: List<LineItem>): Int =
        lunchiliciousClient.addLineItems(lineItems)

    override suspend fun getAllOrders(): List<FoodOrder> =
        lunchiliciousClient.getAllOrders()
}