package com.scottb4.lunchilicious.ui


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scottb4.lunchilicious.data.MenuItem

@Composable
fun OrderScreen (
    lunchiliciousUiState: LunchiliciousUiState,
    menu: List<MenuItem>,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory),
) {

    //val menu by lunchiliciousViewModel.getAllMenuItems().collectAsState(initial = emptyList())

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