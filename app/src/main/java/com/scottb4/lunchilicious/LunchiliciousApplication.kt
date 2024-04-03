package com.scottb4.lunchilicious

import android.app.Application
import com.scottb4.lunchilicious.data.FoodOrderRepositoryImplementation
import com.scottb4.lunchilicious.data.LineItemRepositoryImplementation
import com.scottb4.lunchilicious.data.MenuItemRepositoryImplementation
import com.scottb4.lunchilicious.data.LunchiliciousDatabase
import com.scottb4.lunchilicious.domain.FoodOrderRepository
import com.scottb4.lunchilicious.domain.LineItemRepository
import com.scottb4.lunchilicious.domain.MenuItemRepository

class LunchiliciousApplication: Application() {
    lateinit var menuItemRepository: MenuItemRepository
    lateinit var foodOrderRepository: FoodOrderRepository
    lateinit var lineItemRepository: LineItemRepository
    override fun onCreate() {
        super.onCreate()
        menuItemRepository =
         MenuItemRepositoryImplementation(LunchiliciousDatabase.getDatabase(this))
        foodOrderRepository =
            FoodOrderRepositoryImplementation(LunchiliciousDatabase.getDatabase(this))
        lineItemRepository =
            LineItemRepositoryImplementation(LunchiliciousDatabase.getDatabase(this))
    }
}