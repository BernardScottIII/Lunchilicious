package com.scottb4.lunchilicious.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
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
                lunchiliciousUiState = lunchiliciousViewModel.lunchiliciousUiState,
                lunchiliciousViewModel = lunchiliciousViewModel,
                navigateToConfirmationScreen = {
                    navController.navigate(LunchiliciousScreen.ConfirmationScreen)
                },
                navigateToNewItemScreen = {
                    navController.navigate(LunchiliciousScreen.NewItemScreen)
                },
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

