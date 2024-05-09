package com.scottb4.lunchilicious.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scottb4.lunchilicious.data.MenuItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuItemsColumn(
    menu: List<MenuItem>,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory),
) {
    LazyColumn (
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(6.dp)
    ) {
        items (menu) { menuItem ->
            StatelessMenuItem(
                menuItem = menuItem,
                modifier = modifier
                    .background(Color(0xFFbac4f5))
            )
            Row {
                Checkbox(
                    checked = lunchiliciousViewModel.selectedMenuItems.contains(menuItem),
                    onCheckedChange = {
                        if (lunchiliciousViewModel.selectedMenuItems.contains(menuItem)) {
                            lunchiliciousViewModel.removeMenuItem(menuItem)
                        }
                        else {
                            lunchiliciousViewModel.selectMenuItem(menuItem)
                        }
                    }
                )
//                Spacer(
//                    modifier = modifier.weight(1F)
//                )
//                ElevatedButton(
//                    enabled = true,
//                    shape = CircleShape,
//                    modifier = modifier
//                        .padding(6.dp),
//                    onClick = {
//                        /* TODO */
//                    }
//                ) {
//                    Text(
//                        text = if (lunchiliciousViewModel.detailsValueList.contains(menuItem)) {
//                            "Hide Details"
//                        } else {
//                            "Show Details"
//                        },
//                        textAlign = TextAlign.End,
//                        modifier = modifier
//                            .align(Alignment.CenterVertically)
//                            .padding(end = 6.dp),
//                    )
//                }
                Text(
                    text = if (lunchiliciousViewModel.showingMenuItemDetails.contains(menuItem)) {
                        "Hide Details"
                    } else {
                        "Show Details"
                    },
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .weight(1F)
                        .align(Alignment.CenterVertically)
                        .padding(end = 6.dp),
                )
                Switch (
                    checked = lunchiliciousViewModel.showingMenuItemDetails.contains(menuItem),
                    onCheckedChange = {
                        if(lunchiliciousViewModel.showingMenuItemDetails.contains(menuItem)) {
                            lunchiliciousViewModel.hideMenuItemDetails(menuItem)
                        }
                        else {
                            lunchiliciousViewModel.showMenuItemDetails(menuItem)
                        }
                    }
                )
            }
            if (lunchiliciousViewModel.showingMenuItemDetails.contains(menuItem)) {
                Text(
                    text = menuItem.description,
                    modifier = modifier
                        .padding(bottom = 6.dp)
                )
            }

        }
    }
}