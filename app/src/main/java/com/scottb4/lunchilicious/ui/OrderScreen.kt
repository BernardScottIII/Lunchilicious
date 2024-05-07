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
}