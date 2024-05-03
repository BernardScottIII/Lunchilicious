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
    version = 20,
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
            MenuItem(type = "Hoagie", name = "BLT Hoagie", description = "Cold, Onion, lettuce, tomato", unitPrice = 6.95),
            MenuItem(type = "Hoagie", name = "Cheese Hoagie", description = "Cheese, mayos, lettuce, tomato", unitPrice = 6.95),
            MenuItem(type = "Pizza", name = "Plain Pizza", description = "cheese and tomato", unitPrice = 9.50),
            MenuItem(type = "Side", name = "Fries", description = "large hot fries", unitPrice = 2.95),
            MenuItem(type = "Side", name = "Gravy Fries", description = "Fries with gravy on top", unitPrice = 3.95),
            MenuItem(type = "Entree", name = "Raspberry Chicken", description = "Fried chicken topped with raspberry sauce and pineapple salsa", unitPrice = 10.00),
            MenuItem(type = "Entree", name = "Dragon & Phoenix", description = "Shrimp w. garlic sauce & chunk chicken fried in spicy sauce", unitPrice = 12.00),
            MenuItem(type = "Burrito", name = "Everymeat Burrito", description = "Chicken, beef, pork, lobster, shrimp, fish, duck, lamb, turkey, bison, cornish game hen, goose, pheasant, qual, rabbit, squab, venison, boar, alligator, antelope, caribou, elk, ostrich, turtle, rattlesnake, and kangaroo", unitPrice = 49.95),
            MenuItem(type = "Hoagie", name = "Beer-Battered Cod Sandwich", description = "Fried cod filet and tartar sauce on brioche bun", unitPrice = 8.95),
            MenuItem(type = "Pizza", name = "Margherita Pizza", description = "Fresh tomatoes, basil and mozzarella on neopolitan crust", unitPrice = 14.95),
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