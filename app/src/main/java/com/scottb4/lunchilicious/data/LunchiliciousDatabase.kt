package com.scottb4.lunchilicious.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.scottb4.lunchilicious.Repository

@Database(entities = [MenuItem::class, FoodOrder::class, LineItem::class], version = 6, exportSchema = false)
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
                    .addCallback(object: Callback() {
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            GlobalScope.launch(context = Dispatchers.IO) {
                                val menuItemDao = getDatabase(context).menuItemDao()
                                prepopulateMenuItems(menuItemDao)
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }

        private suspend fun prepopulateMenuItems (menuItemDao: MenuItemDao) {
            menuItemDao.deleteAll()
            val menuItems = Repository.getItems()
            menuItems.forEach { item ->
                menuItemDao.insert(item)
            }
        }
    }
}