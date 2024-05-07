package com.scottb4.lunchilicious.ui



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LunchiliciousBottomBar(
    navigateToConfirmationScreen: () -> Unit,
    navigateToNewItemScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar {
        IconButton(onClick = { navigateToConfirmationScreen() }) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "View Cart"
            )
        }
        IconButton(onClick = { navigateToNewItemScreen() }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add New Item"
            )
        }
//        Column (
//            verticalArrangement = Arrangement.Bottom,
//            modifier = modifier.height(IntrinsicSize.Max)
//        ){
//
//            ElevatedButton(
//                enabled = true,
//                shape = CircleShape,
//                modifier = modifier
//                    .fillMaxWidth()
//                    .weight(1F)
//                    .padding(6.dp),
//                onClick = {
//                    navigateToConfirmationScreen()
//                }
//            ) {
//                Text(text = "View Cart")
//            }
//            ElevatedButton(
//                enabled = true,
//                shape = CircleShape,
//                modifier = modifier
//                    .fillMaxWidth()
//                    .weight(1F)
//                    .padding(6.dp),
//                onClick = {
//                    navigateToNewItemScreen()
//                }
//            ) {
//                Text(text = "Add Item")
//            }
//        }
    }
}