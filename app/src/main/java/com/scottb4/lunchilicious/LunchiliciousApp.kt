package com.scottb4.lunchilicious

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.Composable
import com.scottb4.lunchilicious.data.LunchiliciousDatabase
import com.scottb4.lunchilicious.data.LunchiliciousRepoImpl
import com.scottb4.lunchilicious.domain.LunchiliciousRepo
import com.scottb4.lunchilicious.ui.AppNavigator

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
