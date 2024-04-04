package com.scottb4.lunchilicious.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object LunchiliciousScreen {
    const val OrderScreen = "OrderScreen"
    const val ConfirmationScreen = "ConfirmationScreen"
}

@Composable
fun AppNavigator(
    lunchiliciousViewModel: LunchiliciousViewModel = LunchiliciousViewModel(),
    menuItemViewModel: MenuItemViewModel = viewModel(factory = MenuItemViewModel.Factory)
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LunchiliciousScreen.OrderScreen
    ) {
        composable(LunchiliciousScreen.OrderScreen) {
            OrderScreen(
                lunchiliciousViewModel = lunchiliciousViewModel,
                menuItemViewModel = menuItemViewModel,
                navigateToConfirmationScreen = {
                    navController.navigate(LunchiliciousScreen.ConfirmationScreen)
                }
            )
        }
        composable(LunchiliciousScreen.ConfirmationScreen) {
            ConfirmationScreen(
                lunchiliciousViewModel = lunchiliciousViewModel,
                navigateToOrderScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}