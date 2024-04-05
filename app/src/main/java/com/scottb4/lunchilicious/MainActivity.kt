package com.scottb4.lunchilicious

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.scottb4.lunchilicious.ui.AppNavigator
import com.scottb4.lunchilicious.ui.FoodOrderViewModel
import com.scottb4.lunchilicious.ui.LineItemViewModel
import com.scottb4.lunchilicious.ui.LunchiliciousViewModel
import com.scottb4.lunchilicious.ui.MenuItemViewModel
import com.scottb4.lunchilicious.ui.theme.LunchiliciousTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lunchiliciousViewModel: LunchiliciousViewModel by viewModels()
        val menuItemViewModel: MenuItemViewModel by viewModels(
            factoryProducer = {
                MenuItemViewModel.Factory
            }
        )
        val lineItemViewModel: LineItemViewModel by viewModels(
            factoryProducer = {
                LineItemViewModel.Factory
            }
        )
        val foodOrderViewModel: FoodOrderViewModel by viewModels(
            factoryProducer = {
                FoodOrderViewModel.Factory
            }
        )

        setContent {
            LunchiliciousTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(
                        lunchiliciousViewModel = lunchiliciousViewModel,
                        menuItemViewModel = menuItemViewModel,
                        lineItemViewModel = lineItemViewModel,
                        foodOrderViewModel = foodOrderViewModel
                    )
                    //DatabaseListView()
                }
            }
        }
    }
}