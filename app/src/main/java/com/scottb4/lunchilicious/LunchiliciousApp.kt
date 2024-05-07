package com.scottb4.lunchilicious

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.compose.rememberNavController
import com.scottb4.lunchilicious.data.LunchiliciousDatabase
import com.scottb4.lunchilicious.data.LunchiliciousRepoImpl
import com.scottb4.lunchilicious.domain.LunchiliciousRepo
import com.scottb4.lunchilicious.ui.AppNavigator
import com.scottb4.lunchilicious.ui.LunchiliciousBottomBar
import com.scottb4.lunchilicious.ui.LunchiliciousTopBar
import com.scottb4.lunchilicious.ui.LunchiliciousViewModel

class LunchiliciousApplication: Application() {
    lateinit var lunchiliciousRepo: LunchiliciousRepo
    override fun onCreate() {
        super.onCreate()
        lunchiliciousRepo =
         LunchiliciousRepoImpl(LunchiliciousDatabase.getDatabase(this))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LunchiliciousApp() {
    AppNavigator()
}
