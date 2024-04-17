package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

private fun validateDoubleInput (
    input: String
): Boolean {
    return (input.matches("[0-9]*[.]+[0-9]*[.]+[0-9]*".toRegex())
            || input.indexOf("-") > -1
            || input.indexOf(" ") > -1
            )
}

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
        ) {
            lunchiliciousViewModel.tempMenuItemType = it
        }
        StatelessNewMenuItemInput(
            value = lunchiliciousViewModel.tempMenuItemName,
            label = { Text(text = "Menu Item Name") },
        ) {
            lunchiliciousViewModel.tempMenuItemName = it
        }
        StatelessNewMenuItemInput(
            value = lunchiliciousViewModel.tempMenuItemDesc,
            label = { Text(text = "Menu Item Description") },
        ) {
            lunchiliciousViewModel.tempMenuItemDesc = it
        }
        StatelessNewMenuItemInput(
            value = lunchiliciousViewModel.tempMenuItemPrice,
            label = { Text(text = "Menu Item Price") },
            prefix = { Text(text = "$") },
            placeholder = { Text(text = "0.00")},
            isError = validateDoubleInput(lunchiliciousViewModel.tempMenuItemPrice),
            keyboardType = KeyboardType.Number
        ) {
            lunchiliciousViewModel.tempMenuItemPrice = it
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
                    lunchiliciousViewModel.tempMenuItemType = ""
                    lunchiliciousViewModel.tempMenuItemName = ""
                    lunchiliciousViewModel.tempMenuItemDesc = ""
                    lunchiliciousViewModel.tempMenuItemPrice = ""
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
                    navigateToOrderScreen()
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