package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.scottb4.lunchilicious.data.LineItem

@Composable
fun LineItemsColumn (
    lineItems: List<LineItem>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier
    ) {
        lineItems.forEach {
            /* TODO: implement getMenuItemStream() to retrieve all of the menuItem's info
                     given a lineItem's itemId*/
            Text(text = it.itemId.toString())
            Text(text = it.lineNum.toString())
            Text(text = it.quantity.toString())
        }
    }
}