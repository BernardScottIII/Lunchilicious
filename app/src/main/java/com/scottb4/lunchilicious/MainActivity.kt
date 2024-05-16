package com.scottb4.lunchilicious

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.scottb4.lunchilicious.ui.theme.LunchiliciousTheme
import com.scottb4.lunchilicious.workers.MyWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val myWorkRequest: PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("MyWork",
                ExistingPeriodicWorkPolicy.KEEP,
                myWorkRequest)

        //Cancel background work
//        WorkManager.getInstance(this)
//            .cancelUniqueWork("MyWork")

        val workInfo: Flow<WorkInfo> =
            WorkManager.getInstance(this)
                .getWorkInfoByIdFlow(myWorkRequest.id)

        lifecycleScope.launch {
            workInfo.collect { workInfo ->
                Log.i("WORK_STATUS", workInfo.state.toString())}
        }

        setContent {
            LunchiliciousTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LunchiliciousApp()
                }
            }
        }
    }
}