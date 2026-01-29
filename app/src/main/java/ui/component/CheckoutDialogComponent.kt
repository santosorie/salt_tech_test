package com.example.salttech.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.salttech.R
import com.example.salttech.ui.theme.FiraSans

@Composable
fun CheckoutDialogComponent(
    dialogText: String,
    onCloseButtonClicked: () -> Unit
) {
    Dialog (
        onDismissRequest = { },
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    colorResource(R.color.salt_white_ice_break),
                    RoundedCornerShape(16.dp)
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Success!",
                fontFamily = FiraSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = colorResource(R.color.salt_dark_hot)
            )

            Text(
                dialogText,
                fontFamily = FiraSans,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.salt_dark_hot),
                modifier = Modifier.padding(top = 12.dp)
            )

            RoundedButtonComponent(
                "Close",
                ButtonStyle.ACTIVATED,
                Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            ) {
                onCloseButtonClicked()
            }
        }
    }
}