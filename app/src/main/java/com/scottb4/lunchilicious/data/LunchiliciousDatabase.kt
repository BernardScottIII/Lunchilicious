package com.scottb4.lunchilicious.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuItem::class, FoodOrder::class, LineItem::class], version = 4, exportSchema = false)
abstract class LunchiliciousDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
    abstract fun foodOrderDao(): FoodOrderDao
    abstract fun lineItemDao(): LineItemDao

    companion object {
        @Volatile
        private var Instance: LunchiliciousDatabase? = null

        fun getDatabase(context: Context): LunchiliciousDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, LunchiliciousDatabase::class.java, "my_lunchilicious_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}