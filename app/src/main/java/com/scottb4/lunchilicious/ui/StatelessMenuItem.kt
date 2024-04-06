package com.scottb4.lunchilicious.ui

import android.content.Context
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scottb4.lunchilicious.R
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
                fontSize = 35.sp,
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