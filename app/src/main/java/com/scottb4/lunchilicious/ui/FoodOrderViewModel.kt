package com.scottb4.lunchilicious.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scottb4.lunchilicious.LunchiliciousApplication
import com.scottb4.lunchilicious.data.FoodOrder
import com.scottb4.lunchilicious.domain.FoodOrderRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FoodOrderViewModel (
    private val foodOrderRepository: FoodOrderRepository
): ViewModel() {
    fun createNewFoodOrder(totalCost: Double): Long {
        var result: Long = 0L
        viewModelScope.launch {
            result = foodOrderRepository.insertFoodOrder(
                FoodOrder(total_cost = totalCost)
            )
        }
        return result
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