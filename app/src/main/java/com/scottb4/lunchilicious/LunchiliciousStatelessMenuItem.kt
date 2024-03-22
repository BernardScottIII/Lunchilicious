package com.scottb4.lunchilicious

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.text.NumberFormat

@Composable
fun StatelessMenuItem(
    menuItem: MenuItem,
    modifier: Modifier = Modifier
) {
    Text(
        text = "id = ${menuItem.id}",
        modifier = modifier
    )
    Text(
        text = menuItem.type,
        modifier = modifier
    )
    Text(
        text = menuItem.name,
        modifier = modifier
    )
    Text(
        text = "unitPrice = ${NumberFormat.getCurrencyInstance().format(menuItem.price)}",
        modifier = modifier
    )
}