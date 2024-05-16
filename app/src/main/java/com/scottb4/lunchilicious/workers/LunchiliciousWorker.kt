package com.scottb4.lunchilicious.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.scottb4.lunchilicious.data.LunchiliciousDatabase
import com.scottb4.lunchilicious.data.LunchiliciousRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

        private val lunchiliciousRepo = LunchiliciousRepoImpl(LunchiliciousDatabase.getDatabase(applicationContext))
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                periodicLog()

                // Make the API call here
                lunchiliciousRepo.getMenuItems()

                Result.success()
            } catch (throwable: Throwable) {
                Log.i("Error", throwable.toString())
                Result.failure()
            }
        }
    }
    private suspend fun periodicLog() {
        delay(100)
        Log.i("WORKER: ", System.currentTimeMillis().toString())
    }
}