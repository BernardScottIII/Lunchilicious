package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scottb4.lunchilicious.data.FoodOrder

@Composable
fun FoodOrdersColumn(
    orders: List<FoodOrder>,
    orderItemUiState: OrderItemUiState,
    navigateToOrderDetailsScreen: (orderId: String) -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory),
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(6.dp)
    ) {
        items (orders) { foodOrder ->
            StatelessFoodOrder(
                foodOrder = foodOrder,
                modifier = modifier
                    .background(Color(0xFF3DDC97))
            )
            Row {
                ElevatedButton(
                    enabled = true,
                    shape = CircleShape,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start = 24.dp,
                            top = 6.dp,
                            end = 24.dp,
                            bottom = 6.dp
                        ),
                    onClick = {
                        lunchiliciousViewModel.getLineItemsByOrderId(foodOrder.orderId)
                        navigateToOrderDetailsScreen(foodOrder.orderId)
                    }
                ) {
                    Text(text = "Show Order Details")
                }
//                Switch (
//                    checked = lunchiliciousViewModel.showingFoodOrderDetails.contains(foodOrder),
//                    onCheckedChange = {
//                        if(lunchiliciousViewModel.showingFoodOrderDetails.contains(foodOrder)) {
//                            lunchiliciousViewModel.hideFoodOrderDetails(foodOrder)
//                        }
//                        else {
//                            lunchiliciousViewModel.showFoodOrderDetails(foodOrder)
//
//                        }
//                    }
//                )
            }
            if (lunchiliciousViewModel.showingFoodOrderDetails.contains(foodOrder)) {
                // TODO: make details show every line item
            }
        }
    }
}