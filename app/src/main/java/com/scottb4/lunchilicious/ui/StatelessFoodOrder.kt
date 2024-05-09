package com.scottb4.lunchilicious.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.scottb4.lunchilicious.data.FoodOrder
import java.text.NumberFormat

@Composable
fun StatelessFoodOrder(
    foodOrder: FoodOrder,
    modifier: Modifier = Modifier
) {
    Row (modifier) {
        Column (
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = foodOrder.orderId,
                modifier = modifier,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                lineHeight = 40.sp
            )
            Text(
                text = "Placed on: ${foodOrder.orderDate}",
                modifier = modifier,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "totalCost = ${NumberFormat.getCurrencyInstance().format(foodOrder.totalCost)}",
                modifier = modifier,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
        }
    }
}