package com.scottb4.lunchilicious.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items

@Composable
fun OrderScreen (
    navigateToConfirmationScreen: () -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = LunchiliciousViewModel(),
    menuItemViewModel: MenuItemViewModel = viewModel(factory = MenuItemViewModel.Factory),
    lineItemViewModel: LineItemViewModel = viewModel(factory = LineItemViewModel.Factory),
    foodOrderViewModel: FoodOrderViewModel = viewModel(factory = FoodOrderViewModel.Factory)
) {
    val menu by menuItemViewModel.getAllMenuItems().collectAsState(initial = emptyList())

    LazyColumn (
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 0.dp,
                top = 0.dp,
                end = 0.dp,
                bottom = 72.dp
            ),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(6.dp)
    ) {
        items (menu) { menuItem ->
            Log.i("MENU", "Displaying menuItem #${menuItem.id.toInt()}")
            StatelessMenuItem(
                menuItem = menuItem,
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color(0xFF808080))
            )
            Row {
                Text(
                    modifier = modifier
                        .weight(1F),
                    text = if (lunchiliciousViewModel.detailsValueList[menuItem.id.toInt()-1]) {
                        "Hide Details"
                    } else {
                        "Show Details"
                    }
                )
                Switch (
                    checked = lunchiliciousViewModel.detailsValueList[menuItem.id.toInt()-1],
                    onCheckedChange = {
                        lunchiliciousViewModel.detailsValueList[menuItem.id.toInt() - 1] =
                            !lunchiliciousViewModel.detailsValueList[menuItem.id.toInt() - 1]
                    }
                )
            }
            if (lunchiliciousViewModel.detailsValueList[menuItem.id.toInt()-1]) {
                Text(text = menuItem.description)
            }
            Checkbox(
                checked = lunchiliciousViewModel.checkboxValueList[menuItem.id.toInt()-1],
                onCheckedChange = {
                    lunchiliciousViewModel.checkboxValueList[menuItem.id.toInt()-1] = !lunchiliciousViewModel.checkboxValueList[menuItem.id.toInt()-1]
                }
            )
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
                .padding(24.dp),
            onClick = {
                navigateToConfirmationScreen()

            }
        ) {
            Text(text = "View Cart")
        }
    }
}