package com.scottb4.lunchilicious

import android.app.Application
import com.scottb4.lunchilicious.data.LunchiliciousDatabase
import com.scottb4.lunchilicious.data.LunchiliciousRepositoryImplementation
import com.scottb4.lunchilicious.domain.LunchiliciousRepository

class LunchiliciousApplication: Application() {
    lateinit var lunchiliciousRepository: LunchiliciousRepository
    override fun onCreate() {
        super.onCreate()
        lunchiliciousRepository =
         LunchiliciousRepositoryImplementation(LunchiliciousDatabase.getDatabase(this))
    }
}