package com.scottb4.lunchilicious.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FoodOrderSearchForm(
    navigateToOrderDetailsScreen: (orderId: String) -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel
) {
    var orderIdInput by rememberSaveable{ mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        StatelessNewMenuItemInput(
            value = orderIdInput,
            label = { Text(text = "Order ID") },
            placeholder = { Text(text = "lastfx-MM-dd-HH-mm-ss")}
        ) {
            orderIdInput = it
        }
        Spacer(modifier = Modifier.weight(1f))
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
                Log.i("ORDERID", "Searching for: $orderIdInput")
                lunchiliciousViewModel.getLineItemsByOrderId(orderIdInput)
                navigateToOrderDetailsScreen(orderIdInput)
            }
        ) {
            Text(text = "Search")
        }
    }
}