package com.scottb4.lunchilicious.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [MenuItem::class, FoodOrder::class, LineItem::class],
    version = 19,
    exportSchema = false
)
abstract class LunchiliciousDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
    abstract fun foodOrderDao(): FoodOrderDao
    abstract fun lineItemDao(): LineItemDao

    companion object {
        @Volatile
        private var Instance: LunchiliciousDatabase? = null
        val items: Array<MenuItem> = arrayOf(
            MenuItem(type = "Hoagie", name = "BLT Hoagie", description = "Cold, Onion, lettuce, tomato", unit_price = 6.95),
            MenuItem(type = "Hoagie", name = "Cheese Hoagie", description = "Cheese, mayos, lettuce, tomato", unit_price = 6.95),
            MenuItem(type = "Pizza", name = "Plain Pizza", description = "cheese and tomato", unit_price = 9.50),
            MenuItem(type = "Side", name = "Fries", description = "large hot fries", unit_price = 2.95),
            MenuItem(type = "Side", name = "Gravy Fries", description = "Fries with gravy on top", unit_price = 3.95),
            MenuItem(type = "Entree", name = "Raspberry Chicken", description = "Fried chicken topped with raspberry sauce and pineapple salsa", unit_price = 10.00),
            MenuItem(type = "Entree", name = "Dragon & Phoenix", description = "Shrimp w. garlic sauce & chunk chicken fried in spicy sauce", unit_price = 12.00),
            MenuItem(type = "Burrito", name = "Everymeat Burrito", description = "Chicken, beef, pork, lobster, shrimp, fish, duck, lamb, turkey, bison, cornish game hen, goose, pheasant, qual, rabbit, squab, venison, boar, alligator, antelope, caribou, elk, ostrich, turtle, rattlesnake, and kangaroo", unit_price = 49.95),
            MenuItem(type = "Hoagie", name = "Beer-Battered Cod Sandwich", description = "Fried cod filet and tartar sauce on brioche bun", unit_price = 8.95),
            MenuItem(type = "Pizza", name = "Margherita Pizza", description = "Fresh tomatoes, basil and mozzarella on neopolitan crust", unit_price = 14.95),
        )

        fun getDatabase(context: Context): LunchiliciousDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, LunchiliciousDatabase::class.java, "my_lunchilicious_database")
                    .addCallback(object: Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            GlobalScope.launch(context = Dispatchers.IO) {
                                val menuItemDao = getDatabase(context).menuItemDao()
                                val foodOrderDao = getDatabase(context).foodOrderDao()
                                val lineItemDao = getDatabase(context).lineItemDao()
                                prepopulateMenuItems(menuItemDao, foodOrderDao, lineItemDao)
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }

        private suspend fun prepopulateMenuItems (
            menuItemDao: MenuItemDao,
            foodOrderDao: FoodOrderDao,
            lineItemDao: LineItemDao
        ) {
            lineItemDao.deleteAll()
            lineItemDao.deletePrimaryKeyIndex()

            foodOrderDao.deleteAll()
            foodOrderDao.deletePrimaryKeyIndex()

            menuItemDao.deleteAll()
            menuItemDao.deletePrimaryKeyIndex()

            items.forEach { item ->
                menuItemDao.insert(item)
            }
        }
    }
}