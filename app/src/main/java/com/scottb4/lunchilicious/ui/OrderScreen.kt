package com.scottb4.lunchilicious.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun OrderScreen (
    lunchiliciousUiState: LunchiliciousUiState,
    navigateToConfirmationScreen: () -> Unit,
    navigateToNewItemScreen: () -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory),
) {

    val menu by lunchiliciousViewModel.getAllMenuItems().collectAsState(initial = emptyList())

    when (lunchiliciousUiState) {
        is LunchiliciousUiState.Success -> MenuItemsColumn(
            menu = menu + lunchiliciousUiState.menuItems,
            modifier = modifier,
            lunchiliciousViewModel = lunchiliciousViewModel,
        )
        is LunchiliciousUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
        is LunchiliciousUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
    }
    Column (
        verticalArrangement = Arrangement.Bottom
    ){
        Row (
            verticalAlignment = Alignment.Bottom
        ){
            ElevatedButton(
                enabled = true,
                shape = CircleShape,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp,
                        end = 24.dp,
                        bottom = 0.dp,
                        start = 24.dp
                    ),
                onClick = {
                    navigateToConfirmationScreen()
                }
            ) {
                Text(text = "View Cart")
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
                        top = 6.dp,
                        end = 24.dp,
                        bottom = 6.dp,
                        start = 24.dp
                    ),
                onClick = {
                    navigateToNewItemScreen()
                }
            ) {
                Text(text = "Add Item")
            }
        }
    }
}