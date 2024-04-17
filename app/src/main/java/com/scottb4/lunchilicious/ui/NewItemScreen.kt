package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scottb4.lunchilicious.data.MenuItem

@Composable
fun NewItemScreen(
    navigateToOrderScreen: () -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory)
) {
    Text(text = "Add new Menu Item:")
    Column {
        StatelessNewMenuItemInput(
            value = lunchiliciousViewModel.tempMenuItemType,
            label = { Text(text = "Menu Item Type") },
            isError = lunchiliciousViewModel.validateTempMenuItemType()
        ) {
            lunchiliciousViewModel.updateTempMenuItemType(it)
        }
        StatelessNewMenuItemInput(
            value = lunchiliciousViewModel.tempMenuItemName,
            label = { Text(text = "Menu Item Name") },
            isError = lunchiliciousViewModel.validateTempMenuItemName()
        ) {
            lunchiliciousViewModel.updateTempMenuItemName(it)
        }
        StatelessNewMenuItemInput(
            value = lunchiliciousViewModel.tempMenuItemDesc,
            label = { Text(text = "Menu Item Description") },
            isError = lunchiliciousViewModel.validateTempMenuItemDesc()
        ) {
            lunchiliciousViewModel.updateTempMenuItemDesc(it)
        }
        StatelessNewMenuItemInput(
            value = lunchiliciousViewModel.tempMenuItemPrice,
            label = { Text(text = "Menu Item Price") },
            prefix = { Text(text = "$") },
            placeholder = { Text(text = "0.00")},
            isError = lunchiliciousViewModel.validateTempMenuItemPrice(),
            keyboardType = KeyboardType.Number
        ) {
            lunchiliciousViewModel.updateTempMenuItemPrice(it)
        }
        Spacer(modifier = Modifier.weight(1f))
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
                    lunchiliciousViewModel.clearTempMenuItemFields()
                }
            ) {
                Text(text = "Reset")
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
                        bottom = 6.dp
                    ),
                onClick = {
                    lunchiliciousViewModel.setTempMenuItemValidation(false)
                    if (
                        lunchiliciousViewModel.tempMenuItemType != "" &&
                        lunchiliciousViewModel.tempMenuItemName != "" &&
                        lunchiliciousViewModel.tempMenuItemDesc != "" &&
                        lunchiliciousViewModel.tempMenuItemPrice != ""
                    ) {
                        lunchiliciousViewModel.insertMenuItem(
                            MenuItem(
                                type = lunchiliciousViewModel.tempMenuItemType,
                                name = lunchiliciousViewModel.tempMenuItemName,
                                description = lunchiliciousViewModel.tempMenuItemDesc,
                                unit_price = lunchiliciousViewModel.tempMenuItemPrice.toDouble()
                            )
                        )
                        lunchiliciousViewModel.clearTempMenuItemFields()
                        navigateToOrderScreen()
                    } else {
                        lunchiliciousViewModel.setTempMenuItemValidation(true)
                    }
                }
            ) {
                Text(text = "Save Item")
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
                        bottom = 6.dp
                    ),
                onClick = {
                    navigateToOrderScreen()
                }
            ) {
                Text(text = "Continue Shopping")
            }
        }
    }
}