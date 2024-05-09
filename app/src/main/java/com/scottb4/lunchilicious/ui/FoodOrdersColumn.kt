package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
                Text(
                    text = if (lunchiliciousViewModel.showingFoodOrderDetails.contains(foodOrder)) {
                        "Hide Items"
                    } else {
                        "Show Items"
                    },
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .weight(1F)
                        .align(Alignment.CenterVertically)
                        .padding(end = 6.dp),
                )
                Switch (
                    checked = lunchiliciousViewModel.showingFoodOrderDetails.contains(foodOrder),
                    onCheckedChange = {
                        if(lunchiliciousViewModel.showingFoodOrderDetails.contains(foodOrder)) {
                            lunchiliciousViewModel.hideFoodOrderDetails(foodOrder)
                        }
                        else {
                            lunchiliciousViewModel.showFoodOrderDetails(foodOrder)

                        }
                    }
                )
            }
            if (lunchiliciousViewModel.showingFoodOrderDetails.contains(foodOrder)) {
                // TODO: make details show every line item
            }
        }
    }
}