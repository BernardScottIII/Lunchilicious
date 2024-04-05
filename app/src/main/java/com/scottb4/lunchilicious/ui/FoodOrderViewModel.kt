package com.scottb4.lunchilicious.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scottb4.lunchilicious.LunchiliciousApplication
import com.scottb4.lunchilicious.data.FoodOrder
import com.scottb4.lunchilicious.domain.FoodOrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FoodOrderViewModel (
    private val foodOrderRepository: FoodOrderRepository
): ViewModel() {
    suspend fun createNewFoodOrder(totalCost: Double): Long {
        return foodOrderRepository.insertFoodOrder(
            FoodOrder(total_cost = totalCost)
        )
    }

    fun getAllFoodOrders(): Flow<List<FoodOrder>> {
        return foodOrderRepository.getAllFoodOrdersStream()
    }

    fun insertFoodOrder(foodOrder: FoodOrder) {
        viewModelScope.launch {
            foodOrderRepository.insertFoodOrder(foodOrder)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                        LunchiliciousApplication).foodOrderRepository
                FoodOrderViewModel(
                    foodOrderRepository = myRepository
                )
            }
        }
    }
}