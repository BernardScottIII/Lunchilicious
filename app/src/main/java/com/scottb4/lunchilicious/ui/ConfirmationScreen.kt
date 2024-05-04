package com.scottb4.lunchilicious.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.NumberFormat
import com.scottb4.lunchilicious.data.MenuItem
import kotlinx.coroutines.launch

/* TODO: Disable Scaffold scroll functionality on thsi screen so the "Lunchilicious" banner doesn't
*   block the items at the top
*/
@Composable
fun ConfirmationScreen (
    navigateToOrderScreen: () -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory)
) {
    val candidateLineItems: MutableList<MenuItem> = ArrayList()
    var orderTotal = 0.0
    val scope = rememberCoroutineScope()

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
            lunchiliciousViewModel.selectedMenuItems.forEach { menuItem ->
                item {
                    Text(text = "(id = ${menuItem.id}) ${menuItem.name} => 1 x ${NumberFormat.getCurrencyInstance().format(menuItem.unitPrice)}")
                }
                candidateLineItems.add(menuItem)
                orderTotal += menuItem.unitPrice
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
                    .padding(
                        start = 24.dp,
                        top = 6.dp,
                        end = 24.dp,
                        bottom = 6.dp
                    ),
                onClick = {
                    navigateToOrderScreen()
                }
            ) {
                Text(text = "Continue Shopping")
            }
        }
        Row (
            verticalAlignment = Alignment.Bottom
        ){
            ElevatedButton(
                enabled = true,
                shape = CircleShape,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        top = 6.dp,
                        end = 24.dp,
                        bottom = 24.dp
                    ),
                onClick = {
                    scope.launch {
                        val o_id = lunchiliciousViewModel.createNewFoodOrder(orderTotal)
                        lunchiliciousViewModel.insertAllLineItems(candidateLineItems, o_id)
                    }
                    navigateToOrderScreen()
                }
            ) {
                Text(text = "Checkout")
            }
        }
    }
}
