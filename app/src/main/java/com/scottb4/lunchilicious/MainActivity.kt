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
import com.scottb4.lunchilicious.ui.AppNavigator
import com.scottb4.lunchilicious.ui.LunchiliciousViewModel
import com.scottb4.lunchilicious.ui.OrderScreen
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
}