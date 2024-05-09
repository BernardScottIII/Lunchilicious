package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private object LunchiliciousScreens {
    const val OrderScreen = "OrderScreen"
    const val ConfirmationScreen = "ConfirmationScreen"
    const val NewItemScreen = "NewItemScreen"
    const val PreviousOrdersScreen = "PreviousOrdersScreen"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator(
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory)
) {
    val navController:NavHostController = rememberNavController()
    val lunchiliciousUiState = lunchiliciousViewModel.lunchiliciousUiState
    val foodOrderUiState = lunchiliciousViewModel.foodOrderUiState
    val orderItemUiState = lunchiliciousViewModel.orderItemUiState
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val menu by lunchiliciousViewModel.getAllMenuItems().collectAsState(initial = emptyList())
    val orders by lunchiliciousViewModel.getAllFoodOrders().collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { LunchiliciousTopBar(
            scrollBehavior = scrollBehavior
        ) },
        bottomBar = { LunchiliciousBottomBar(
                navigateToConfirmationScreen = {
                    navController.navigate(LunchiliciousScreens.ConfirmationScreen)
                },
                navigateToNewItemScreen = {
                    navController.navigate(LunchiliciousScreens.NewItemScreen)
                },
                navigateToPreviousOrdersScreen = {
                    navController.navigate(LunchiliciousScreens.PreviousOrdersScreen)
                },
                lunchiliciousViewModel = lunchiliciousViewModel
            )
        },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
        ) {
            NavHost(
                navController = navController,
                startDestination = LunchiliciousScreens.OrderScreen
            ) {
                composable(LunchiliciousScreens.OrderScreen) {
                    OrderScreen(
                        lunchiliciousUiState = lunchiliciousUiState,
                        lunchiliciousViewModel = lunchiliciousViewModel,
                        menu = menu
                    )
                }
                composable(LunchiliciousScreens.ConfirmationScreen) {
                    ConfirmationScreen(
                        lunchiliciousViewModel = lunchiliciousViewModel,
                        navigateToOrderScreen = {
                            navController.popBackStack()
                        }
                    )
                }
                composable(LunchiliciousScreens.NewItemScreen) {
                    NewItemScreen(
                        navigateToOrderScreen = {
                            navController.popBackStack()
                        },
                        lunchiliciousViewModel = lunchiliciousViewModel
                    )
                }
                composable(LunchiliciousScreens.PreviousOrdersScreen) {
                    PreviousOrdersScreen(
                        foodOrderUiState = foodOrderUiState,
                        orderItemUiState = orderItemUiState,
                        orders = orders,
                        lunchiliciousViewModel = lunchiliciousViewModel
                    )
                }
            }
        }
    }
}

