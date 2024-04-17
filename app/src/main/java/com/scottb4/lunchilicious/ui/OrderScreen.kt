package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.style.TextAlign

@Composable
fun OrderScreen (
    navigateToConfirmationScreen: () -> Unit,
    navigateToNewItemScreen: () -> Unit,
    modifier: Modifier = Modifier,
    lunchiliciousViewModel: LunchiliciousViewModel = viewModel(factory = LunchiliciousViewModel.Factory)
) {
    val menu by lunchiliciousViewModel.getAllMenuItems().collectAsState(initial = emptyList())


//    val menuItemImages = listOf(
//        R.drawable.menuItem1,
//        R.drawable.menuitem2,
//        R.drawable.menuItem3,
//        R.drawable.menuItem4,
//        R.drawable.menuItem5,
//        R.drawable.menuItem6,
//        R.drawable.menuItem7,
//        R.drawable.menuItem8,
//        R.drawable.menuItem9,
//        R.drawable.menuItem10,
//    )

    LazyColumn (
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 0.dp,
                top = 0.dp,
                end = 0.dp,
                bottom = 108.dp
            ),
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
//                    checked = lunchiliciousViewModel.checkboxValueList[menuItem.id.toInt()-1],
                    onCheckedChange = {
//                        lunchiliciousViewModel.checkboxValueList[menuItem.id.toInt()-1] = !lunchiliciousViewModel.checkboxValueList[menuItem.id.toInt()-1]
                        if (lunchiliciousViewModel.selectedMenuItems.contains(menuItem)) {
                            lunchiliciousViewModel.removeMenuItem(menuItem)
                        }
                        else {
                            lunchiliciousViewModel.selectMenuItem(menuItem)
                        }
                    }
                )
                Text(
                    text = if (lunchiliciousViewModel.detailsValueList[menuItem.id.toInt()-1]) {
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
                    checked = lunchiliciousViewModel.detailsValueList[menuItem.id.toInt()-1],
                    onCheckedChange = {
                        lunchiliciousViewModel.detailsValueList[menuItem.id.toInt() - 1] =
                            !lunchiliciousViewModel.detailsValueList[menuItem.id.toInt() - 1]
                    }
                )
            }
            if (lunchiliciousViewModel.detailsValueList[menuItem.id.toInt()-1]) {
                Text(
                    text = menuItem.description,
                    modifier = modifier
                        .padding(bottom = 6.dp)
                )
            }

        }
    }
    Column (
        verticalArrangement = Arrangement.Bottom
    ){
        Row (
            verticalAlignment = Alignment.Bottom
        ){
            ElevatedButton(
                enabled = true,
                shape = CircleShape,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp,
                        end = 24.dp,
                        bottom = 0.dp,
                        start = 24.dp
                    ),
                onClick = {
                    navigateToConfirmationScreen()
                }
            ) {
                Text(text = "View Cart")
            }
        }
        Row (
            verticalAlignment = Alignment.Bottom
        ){
            ElevatedButton(
                enabled = true,
                shape = CircleShape,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 6.dp,
                        end = 24.dp,
                        bottom = 6.dp,
                        start = 24.dp
                    ),
                onClick = {
                    navigateToNewItemScreen()
                }
            ) {
                Text(text = "Add Item")
            }
        }
    }
}