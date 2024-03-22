package com.scottb4.lunchilicious

import com.scottb4.lunchilicious.MenuItem

class Repository {
    private val items: Array<MenuItem> = arrayOf(
        MenuItem(1, "Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", 6.95),
        MenuItem(2, "Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", 6.95),
        MenuItem(3, "Pizza", "Plain Pizza", "cheese and tomato", 9.50),
        MenuItem(4, "Side", "Fries", "large hot fries", 2.95),
        MenuItem(5, "Side", "Gravy Fries", "Fries with gravy on top", 3.95),
        MenuItem(6, "Entree", "Raspberry Chicken", "Fried chicken topped with raspberry sauce and pineapple salsa", 10.00),
        MenuItem(7, "Entree", "Dragon & Phoenix", "Shrimp w. garlic sauce & chunk chicken fried in spicy sauce", 12.00),
        MenuItem(8, "Burrito", "Everymeat Burrito", "Chicken, beef, pork, lobster, shrimp, fish, duck, lamb, turkey, bison, cornish game hen, goose, pheasant, qual, rabbit, squab, venison, boar, alligator, antelope, caribou, elk, ostrich, turtle, rattlesnake, and kangaroo", 49.95),
        MenuItem(9, "Hoagie", "Beer-Battered Cod Sandwich", "Fried cod filet and tartar sauce on brioche bun", 8.95),
        MenuItem(10, "Pizza", "Margherita Pizza", "Fresh tomatoes, basil and mozzarella on neopolitan crust", 14.95),
    )

    fun getItems(): Array<MenuItem> { return items }
}