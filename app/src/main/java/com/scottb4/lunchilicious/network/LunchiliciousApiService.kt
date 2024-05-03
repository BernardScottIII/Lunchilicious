package com.scottb4.lunchilicious.network


import com.scottb4.lunchilicious.data.MenuItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val baseUrl = "http://aristotle.cs.scranton.edu/"

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .build()

interface LunchiliciousClient {
    @GET("lunchilicious")
    suspend fun getMenuItems(): List<MenuItem>
}

private var lunchiliciousClient: LunchiliciousClient = retrofit.create(LunchiliciousClient::class.java)