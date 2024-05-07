package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scottb4.lunchilicious.R
import com.scottb4.lunchilicious.data.MenuItem

private object LunchiliciousScreen {
    const val OrderScreen = "OrderScreen"
    const val ConfirmationScreen = "ConfirmationScreen"
    const val NewItemScreen = "NewItemScreen"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator(
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory)
) {
    val navController:NavHostController = rememberNavController()
    val lunchiliciousUiState = lunchiliciousViewModel.lunchiliciousUiState
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { LunchiliciousTopBar(
            scrollBehavior = scrollBehavior
        ) },
        bottomBar = { LunchiliciousBottomBar(
                navigateToConfirmationScreen = {
                    navController.navigate(LunchiliciousScreen.ConfirmationScreen)
                },
                navigateToNewItemScreen = {
                    navController.navigate(LunchiliciousScreen.NewItemScreen)
                }
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
                startDestination = LunchiliciousScreen.OrderScreen
            ) {
navController.currentDestination?.route
                composable(LunchiliciousScreen.OrderScreen) {
                    OrderScreen(
                        lunchiliciousUiState = lunchiliciousUiState,
                        lunchiliciousViewModel = lunchiliciousViewModel,
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
                        navigateToOrderScreen = {
                            navController.popBackStack()
                        },
                        lunchiliciousViewModel = lunchiliciousViewModel
                    )
                }
            }
        }
    }
}

