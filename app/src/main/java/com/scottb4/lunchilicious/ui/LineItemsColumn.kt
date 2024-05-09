package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.scottb4.lunchilicious.data.LineItem
import com.scottb4.lunchilicious.data.MenuItem

@Composable
fun LineItemsColumn (
    lineItems: List<LineItem>,
    lunchiliciousViewModel: LunchiliciousViewModel,
    modifier: Modifier = Modifier
) {
    Column (
        modifier
    ) {
        lineItems.forEach { lineItem ->
            lunchiliciousViewModel.getMenuItemStream(lineItem.itemId).collectAsState(
                initial = null
            ).value?.let { StatelessMenuItem(menuItem = it) }
        }
    }
}