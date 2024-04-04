package com.scottb4.lunchilicious

import com.scottb4.lunchilicious.data.MenuItem

class Repository {
    private val items: Array<MenuItem> = arrayOf(
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

    fun getItems(): Array<MenuItem> { return items }

    companion object {
        fun getItems(repo: Repository = Repository()): Array<MenuItem> =
            repo.getItems()
    }
}