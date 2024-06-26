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
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.scottb4.lunchilicious.data.FoodOrder

@Composable
fun FoodOrdersColumn(
    orders: List<FoodOrder>,
    navigateToOrderDetailsScreen: (orderId: String) -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(6.dp)
    ) {
        items (orders) { foodOrder ->
            if(foodOrder.orderId.contains("scottb4")) {
                StatelessFoodOrder(
                    foodOrder = foodOrder,
                    modifier = modifier
                        .background(Color(0xFF3DDC97))
                )
                Row {
                    Spacer(modifier.weight(1F))
                    ElevatedButton(
                        enabled = true,
                        shape = CircleShape,
                        modifier = modifier
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
                }
            }
        }
    }
}