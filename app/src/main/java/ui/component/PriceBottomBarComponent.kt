package com.example.salttech.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salttech.R
import com.example.salttech.ui.theme.FiraSans

@Composable
fun PriceBottomBarComponent(
    price: String,
    enableCheckoutButton: Boolean,
    onClickCheckout: () -> Unit,
    onClickReset: () -> Unit
) {
    Surface(
        Modifier
            .fillMaxWidth(),
        shadowElevation = 8.dp
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Total:",
                    fontFamily = FiraSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorResource(R.color.salt_dark_hot)
                )

                Text(
                    price,
                    fontFamily = FiraSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorResource(R.color.salt_dark_hot),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (enableCheckoutButton) {
                Column {
                    RoundedButtonComponent(
                        "Checkout",
                        ButtonStyle.ACTIVATED,
                        Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                    ) {
                        onClickCheckout()
                    }

                    RoundedButtonComponent(
                        "Reset",
                        ButtonStyle.SECONDARY,
                        Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth()
                    ) {
                        onClickReset()
                    }
                }
            } else {
                RoundedButtonComponent(
                    "Checkout",
                    ButtonStyle.DEACTIVATED,
                    Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    // disabled do nothing
                }
            }
        }
    }
}