package com.scottb4.lunchilicious

import android.app.Application
import com.scottb4.lunchilicious.data.LunchiliciousDatabase
import com.scottb4.lunchilicious.data.LunchiliciousRepoImpl
import com.scottb4.lunchilicious.domain.LunchiliciousRepo

class LunchiliciousApp: Application() {
    lateinit var lunchiliciousRepo: LunchiliciousRepo
    override fun onCreate() {
        super.onCreate()
        lunchiliciousRepo =
         LunchiliciousRepoImpl(LunchiliciousDatabase.getDatabase(this))
    }
}