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
            Text(text = it.itemId.toString())
            Text(text = it.lineNum.toString())
            Text(text = it.quantity.toString())
        }
    }
}