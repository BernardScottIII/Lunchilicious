package com.scottb4.lunchilicious.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private object LunchiliciousScreen {
    const val OrderScreen = "OrderScreen"
    const val ConfirmationScreen = "ConfirmationScreen"
    const val NewItemScreen = "NewItemScreen"
}

@Composable
fun AppNavigator(
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory)
) {
    val navController:NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LunchiliciousScreen.OrderScreen
    ) {
        composable(LunchiliciousScreen.OrderScreen) {
            OrderScreen(
                lunchiliciousViewModel = lunchiliciousViewModel,
                navigateToConfirmationScreen = {
                    navController.navigate(LunchiliciousScreen.ConfirmationScreen)
                },
                navigateToNewItemScreen = {
                    navController.navigate(LunchiliciousScreen.NewItemScreen)
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
        composable(LunchiliciousScreen.NewItemScreen) {
            NewItemScreen(
                lunchiliciousViewModel = lunchiliciousViewModel,
                navigateToOrderScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}