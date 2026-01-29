package com.example.salttech.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salttech.R
import com.example.salttech.ui.theme.FiraSans

enum class ButtonStyle {
    ACTIVATED,
    DEACTIVATED,
    SECONDARY
}

@Composable
fun RoundedButtonComponent(
    text: String,
    style: ButtonStyle,
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit
) {

    val bgColor: Color
    val fontColor: Color

    when (style) {
        ButtonStyle.ACTIVATED -> {
            bgColor = colorResource(R.color.salt_button_activated)
            fontColor = colorResource(R.color.salt_white_ice)
        }

        ButtonStyle.DEACTIVATED -> {
            bgColor = colorResource(R.color.salt_button_deactivated)
            fontColor = colorResource(R.color.salt_white_ice)
        }

        ButtonStyle.SECONDARY -> {
            bgColor = colorResource(R.color.white)
            fontColor = colorResource(R.color.salt_button_activated)
        }
    }

    Box(
        modifier
            .background(
                bgColor,
                CircleShape
            )
            .then(
                if (style == ButtonStyle.SECONDARY) {
                    Modifier.border(
                        width = 2.dp,
                        color = colorResource(R.color.salt_button_activated),
                        shape = CircleShape
                    )
                } else {
                    Modifier
                }
            )
            .clickable { onClickButton() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            fontFamily = FiraSans,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = fontColor,
            modifier = Modifier.padding(12.dp)
        )
    }
}