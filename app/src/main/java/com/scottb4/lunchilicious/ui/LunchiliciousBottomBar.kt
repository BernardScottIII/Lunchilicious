package com.scottb4.lunchilicious.ui



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun LunchiliciousBottomBar(
    // TODO: just pass the navController? Look at ppt 5
    lunchiliciousViewModel: LunchiliciousViewModel,
    navigateToConfirmationScreen: () -> Unit,
    navigateToNewItemScreen: () -> Unit,
    navigateToPreviousOrdersScreen: () -> Unit,
    navigateToFoodOrderSearchForm: () -> Unit
) {
    BottomAppBar {
        IconButton(onClick = { navigateToConfirmationScreen() }) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "View Cart"
            )
        }
        IconButton(onClick = { navigateToNewItemScreen() }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add New Item"
            )
        }
        IconButton(onClick = { lunchiliciousViewModel.getMenuItems() }) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Add New Item"
            )
        }
        IconButton(onClick = { navigateToPreviousOrdersScreen() }) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Add New Item"
            )
        }
        IconButton(onClick = { navigateToFoodOrderSearchForm() }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Add New Item"
            )
        }
    }
}