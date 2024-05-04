package com.scottb4.lunchilicious.ui


import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.scottb4.lunchilicious.R
import com.scottb4.lunchilicious.data.MenuItem

@Composable
fun OrderScreen (
    lunchiliciousUiState: LunchiliciousUiState,
    navigateToConfirmationScreen: () -> Unit,
    navigateToNewItemScreen: () -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory),
) {
    when (lunchiliciousUiState) {
        is LunchiliciousUiState.Success -> MenuItemsColumn(
            menu = lunchiliciousUiState.menuItems,
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