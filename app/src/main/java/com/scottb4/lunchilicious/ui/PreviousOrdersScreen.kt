package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scottb4.lunchilicious.data.FoodOrder

@Composable
fun PreviousOrdersScreen (
    foodOrderUiState: FoodOrderUiState,
    orderItemUiState: OrderItemUiState,
    orders: List<FoodOrder>,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory),
) {
    when(foodOrderUiState) {
        is FoodOrderUiState.Success -> FoodOrdersColumn(
            orders = orders + foodOrderUiState.foodOrders,
            modifier = modifier,
            lunchiliciousViewModel = lunchiliciousViewModel,
            orderItemUiState = orderItemUiState
        )
        is FoodOrderUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is FoodOrderUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}