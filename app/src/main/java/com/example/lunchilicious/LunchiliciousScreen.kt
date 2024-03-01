package com.example.lunchilicious

import android.content.ClipData.Item
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun LunchiliciousScreen(modifier: Modifier = Modifier): Unit {
    val state = rememberLazyListState()

    val menuItems = listOf(
        StatelessMenuItem(
            id = 1,
            type = "Hoagie",
            name = "BLT Hoagie",
            description = "Cold, Onion, lettuce, tomato",
            unitPrice = 6.95
        ),
        StatelessMenuItem(
            id = 2,
            type = "Hoagie",
            name = "Cheese Hoagie",
            description = "Cheese, mayos, lettuce, tomato",
            unitPrice = 6.95
        ),
        StatelessMenuItem(
            id = 3,
            type = "Pizza",
            name = "Plain Pizza",
            description = "cheese and tomato",
            unitPrice = 9.50
        ),
        StatelessMenuItem(
            id = 4,
            type = "Side",
            name = "Fries",
            description = "large hot fries",
            unitPrice = 2.95
        ),
        StatelessMenuItem(
            id = 5,
            type = "Side",
            name = "Gravy Fries",
            description = "Fries with gravy on top",
            unitPrice = 3.95
        ),
        StatelessMenuItem(
            id = 6,
            type = "Entree",
            name = "Raspberry Chicken",
            description = "Fried chicken topped with raspberry sauce and pineapple salsa",
            unitPrice = 10.00
        ),
        StatelessMenuItem(
            id = 7,
            type = "Entree",
            name = "Dragon & Phoenix",
            description = "Shrimp w. garlic sauce & chunk chicken fried in spicy sauce.",
            unitPrice = 12.00
        ),
        StatelessMenuItem(
            id = 8,
            type = "Hoagie",
            name = "BLT Hoagie",
            description = "Cold, Onion, lettuce, tomato",
            unitPrice = 6.95
        ),
        StatelessMenuItem(
            id = 9,
            type = "Hoagie",
            name = "BLT Hoagie",
            description = "Cold, Onion, lettuce, tomato",
            unitPrice = 6.95
        ),
        StatelessMenuItem(
            id = 10,
            type = "Hoagie",
            name = "BLT Hoagie",
            description = "Cold, Onion, lettuce, tomato",
            unitPrice = 6.95
        )
    )


    StatelessLazyColumn(
        state = state,
        data = 5
    )
}

@Composable
fun StatelessLazyColumn (
    state: LazyListState,
    // Make data a list of objects which are the items
    data: Int,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        state = state,
        modifier = modifier
            .fillMaxWidth()
    ) {// Make the argument data.size
        items(data) { item ->
            Text(text = "Item = $item")
        }
    }
}

@Composable
fun StatelessMenuItem(
    id: Int,
    type: String,
    name: String,
    description: String,
    unitPrice: Double,
    modifier: Modifier = Modifier
): Unit {
    Text(text = "id = $id")
    Text(text = "type = $type")
    Text(text = "name = $name")
    Text(text = "description = $description")
    Text(text = "unitPrice = $unitPrice")
}
