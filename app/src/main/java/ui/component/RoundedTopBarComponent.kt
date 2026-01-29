package com.example.salttech.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salttech.R
import com.example.salttech.ui.theme.FiraSans

@Composable
fun RoundedTopBarComponent(
    totalProduct: String = ""
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(94.dp)
            .clip(
                RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .background(color = colorResource(R.color.salt_red)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(R.drawable.mobile),
            null,
            Modifier
                .padding(start = 20.dp)
                .size(20.dp, 30.dp),
        )

        Column(
            Modifier.padding(start = 20.dp)
        ) {
            Text(
                "Product List",
                fontFamily = FiraSans,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = colorResource(R.color.white)
            )

            if (totalProduct.isNotEmpty()) {
                Text(
                    totalProduct,
                    fontFamily = FiraSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = colorResource(R.color.white)
                )
            }
        }
    }
}