package com.scottb4.lunchilicious

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scottb4.lunchilicious.ui.theme.LunchiliciousTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lunchiliciousViewModel: LunchiliciousViewModel by viewModels()
        setContent {
            LunchiliciousTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(lunchiliciousViewModel)
                }
            }
        }
    }

    object LunchiliciousScreen {
        const val OrderScreen = "OrderScreen"
        const val ConfirmationScreen = "ConfirmationScreen"
    }

    @Composable
    fun AppNavigator(lunchiliciousViewModel: LunchiliciousViewModel = LunchiliciousViewModel()) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = LunchiliciousScreen.OrderScreen
        ) {
            composable(LunchiliciousScreen.OrderScreen) {
                OrderScreen(
                    lunchiliciousViewModel = lunchiliciousViewModel,
                    navigateToConfirmationScreen = {
                        navController.navigate(LunchiliciousScreen.ConfirmationScreen)
                    }
                )
            }
            composable(LunchiliciousScreen.ConfirmationScreen) {
                ConfirmationScreen(
                    lunchiliciousViewModel = lunchiliciousViewModel,
                    navigateToConfirmationScreen = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}