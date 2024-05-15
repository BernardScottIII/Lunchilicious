package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PreviousOrdersScreen (
    foodOrderUiState: FoodOrderUiState,
    navigateToOrderDetailsScreen: (orderId: String) -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel
) {
    when(foodOrderUiState) {
        is FoodOrderUiState.Success -> FoodOrdersColumn(
            orders = foodOrderUiState.foodOrders,
            modifier = modifier,
            lunchiliciousViewModel = lunchiliciousViewModel,
            navigateToOrderDetailsScreen = navigateToOrderDetailsScreen
        )
        is FoodOrderUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is FoodOrderUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}