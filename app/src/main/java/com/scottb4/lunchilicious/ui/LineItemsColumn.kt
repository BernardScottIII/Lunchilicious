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
    //val menu by lunchiliciousViewModel.getAllMenuItems().collectAsState(initial = emptyList())

    Column (
        modifier
    ) {
        lineItems.forEach { lineItem ->
            /* TODO: implement getMenuItemStream() to retrieve all of the menuItem's info
                     given a lineItem's itemId*/
            // Make the column display the MenuItem retrieved from the VM
            lunchiliciousViewModel.getMenuItemStream(lineItem.itemId).collectAsState(
                initial = null
            ).value?.let { StatelessMenuItem(menuItem = it) }

//            Text(text = it.itemId.toString())
//            Text(text = it.lineNum.toString())
//            Text(text = it.quantity.toString())
        }
    }
}