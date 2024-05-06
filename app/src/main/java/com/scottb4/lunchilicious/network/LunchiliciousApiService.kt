package com.scottb4.lunchilicious.network


import com.scottb4.lunchilicious.data.FoodOrder
import com.scottb4.lunchilicious.data.LineItem
import com.scottb4.lunchilicious.data.MenuItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


val baseUrl = "http://aristotle.cs.scranton.edu/"

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .build()

// Retrofit client interfaces
interface LunchiliciousClient {
    @GET("lunchilicious")
    suspend fun getMenuItems(): List<MenuItem>

    @GET("/lunchilicious/menuitem?")
    suspend fun getMenuItemById(@Query("id") id: Int): MenuItem

    @POST("/lunchilicious/addmenuitem")
    suspend fun addMenuItem(@Body menuItem: MenuItem): MenuItem

    @PUT("/lunchilicious/updatemenuitem/{id}")
    suspend fun updateMenuItem(@Query("id") id: Int, @Body menuItem: MenuItem): MenuItem

    @DELETE("/lunchilicious/deletemenuitem/{id}/")
    suspend fun deleteRemoteMenuItem(@Query("id") id: Int): MenuItem

    @GET("/lunchilicious/order?")
    suspend fun getOrderById(@Query("orderId") orderId: Int): FoodOrder

    @POST("/lunchilicious/addorder")
    suspend fun addFoodOrder(@Body foodOrder: FoodOrder): FoodOrder

    @GET("/lunchilicious/lineitems?")
    suspend fun getLineItemsByOrderId(@Query("orderId") orderId: Int): List<LineItem>

    @POST("/lunchilicious/addlineitems")
    suspend fun addLineItems(@Body lineItems: List<LineItem>): Int
}

private var lunchiliciousClient: LunchiliciousClient = retrofit.create(LunchiliciousClient::class.java)