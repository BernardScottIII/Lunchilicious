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
    fun createFoodOrders() {
        viewModelScope.launch {
            (0..3).forEach{ _ ->
                val foodOrder = FoodOrder(
                    id = (0..100).random(),
                    total_cost = 1234.45
                )
                foodOrderRepository.insertFoodOrder(foodOrder)
            }
        }
    }

    fun getAllFoodOrders(): Flow<List<FoodOrder>> {
        return foodOrderRepository.getAllFoodOrdersStream()
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