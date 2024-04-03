package com.scottb4.lunchilicious

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scottb4.lunchilicious.ui.MenuItemViewModel

@Composable
fun DatabaseListView(vm: MenuItemViewModel = viewModel(factory = MenuItemViewModel.Factory)) {
    val menuItemList by vm.getAllMenuItems().collectAsState(initial = emptyList())

    Column {
        Button (
            onClick = {
                //vm.createMenuItems()
            }
        ) {
            Text(text = "Add MenuItems")
        }

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            items(menuItemList) { menuItem ->
                Row {
                    Text(text = menuItem.id.toString())
                    Text(text = menuItem.type)
                    Text(text = menuItem.name)
                    Text(text = menuItem.description)
                    Text(text = menuItem.unit_price.toString())
                }
            }
        }
    }
}