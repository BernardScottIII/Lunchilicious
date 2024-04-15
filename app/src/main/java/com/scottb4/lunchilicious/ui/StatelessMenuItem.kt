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
import com.scottb4.lunchilicious.data.MenuItem
import java.text.NumberFormat

@Composable
fun StatelessMenuItem(
    menuItem: MenuItem,
    modifier: Modifier = Modifier
) {
    Row (modifier) {
//        Image(
//            painter = painterResource(
//                id =
//            ),
//            contentDescription = "BLT Hoagie",
//            modifier = modifier
//                .size(120.dp),
//        )
        Column (
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = menuItem.name,
                modifier = modifier,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                lineHeight = 40.sp
            )
            Text(
                text = menuItem.type,
                modifier = modifier,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "id = ${menuItem.id}",
                modifier = modifier,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraLight
            )
            Text(
                text = "unitPrice = ${NumberFormat.getCurrencyInstance().format(menuItem.unit_price)}",
                modifier = modifier,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
        }
    }
}