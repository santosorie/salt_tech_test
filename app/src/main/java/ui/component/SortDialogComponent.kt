package com.example.salttech.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.salttech.R
import com.example.salttech.ui.screen.SortOption
import com.example.salttech.ui.theme.FiraSans

@Composable
fun SortDialogComponent(
    selectedOption: String,
    onClickOption: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onClickApply: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        val sortOptions = SortOption.entries.map { it.value }

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
            sortOptions.forEach { option ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { onClickOption(option) }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == option,
                        onClick = { onClickOption(option) }
                    )

                    Text(
                        option,
                        fontFamily = FiraSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = colorResource(R.color.salt_dark),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                HorizontalDivider(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = colorResource(R.color.salt_gray)
                )
            }

            RoundedButtonComponent(
                "Apply",
                ButtonStyle.ACTIVATED,
                Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
            ) {
                onClickApply()
            }
        }
    }
}