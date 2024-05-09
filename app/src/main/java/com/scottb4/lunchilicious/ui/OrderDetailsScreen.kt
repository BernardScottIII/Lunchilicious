package com.scottb4.lunchilicious.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

// TODO: Replicate ConfirmationScreen with information from LineItem!!!
@Composable
fun OrderDetailsScreen (
    navigateToOrderScreen: () -> Unit,
    orderId: String,
    orderItemUiState: OrderItemUiState,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory)
) {
    Log.i("ORDERID", "RECIEVED ORDER <- $orderId")


    when (orderItemUiState) {
        is OrderItemUiState.Success -> LineItemsColumn(
            lineItems = orderItemUiState.lineItems,
            lunchiliciousViewModel = lunchiliciousViewModel
        )
        is OrderItemUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is OrderItemUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
    Column {
        Spacer(modifier.weight(1F))
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
            onClick = { navigateToOrderScreen() }
        ) {
            Text(text = "Back to Previous Orders")
        }
    }
}