package com.scottb4.lunchilicious

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.scottb4.lunchilicious.ui.LunchiliciousViewModel
import java.text.NumberFormat

@Composable
fun ConfirmationScreen (
    navigateToOrderScreen: () -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = LunchiliciousViewModel()
) {
    val menu = Repository().getItems()
    var orderTotal = 0.0

    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .weight(1F),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            item { Text(text = "Order Summary") }
            menu.forEach { menuItem ->
                if (lunchiliciousViewModel.checkboxValueList[menuItem.id-1]) {
                    item { Text(text = "${menuItem.name} => 1 x ${NumberFormat.getCurrencyInstance().format(menuItem.price)}") }
                    orderTotal += menuItem.price
                }
            }
        }
        Row (
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ){
            Text(text = "Order Total: ${ NumberFormat.getCurrencyInstance().format(orderTotal) }")
        }
        Row (
            verticalAlignment = Alignment.Bottom
        ){
            ElevatedButton(
                enabled = true,
                shape = CircleShape,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                onClick = {
                    navigateToOrderScreen()
                }
            ) {
                Text(text = "Continue Shopping")
            }
        }
    }
}
