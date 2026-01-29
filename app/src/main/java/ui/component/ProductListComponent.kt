package com.example.salttech.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salttech.R
import com.example.salttech.ui.screen.ProductUiModel
import com.example.salttech.ui.theme.FiraSans

@Composable
fun ProductListComponent(
    products: List<ProductUiModel>,
    quantities: Map<Int, Int>,
    sortName: String,
    onPlusClicked: (productId: Int, stock: Int, price: Double) -> Unit,
    onMinusClicked: (productId: Int, price: Double) -> Unit,
    onClickSortButton: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.salt_white_ice)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.padding(
                start = 20.dp,
                top = 42.dp,
                end = 20.dp
            )
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Image(
                        painterResource(R.drawable.sort_icon),
                        null,
                        Modifier.size(12.dp)
                    )

                    Text(
                        "Sort By:",
                        fontFamily = FiraSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Row(
                    Modifier
                        .widthIn(min = 108.dp) 
                        .height(36.dp)
                        .background(
                            colorResource(
                                R.color.salt_gray
                            ),
                            RoundedCornerShape(8.dp)
                        )
                        .clickable { onClickSortButton() }
                        .padding(start = 12.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        sortName,
                        fontFamily = FiraSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = colorResource(R.color.salt_dark),
                        modifier = Modifier.padding(end = 8.dp),
                        maxLines = 1
                    )

                    Image(
                        painter = painterResource(R.drawable.arrow_down),
                        null,
                        Modifier.size(8.dp)
                    )
                }

            }

            HorizontalDivider(
                Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = colorResource(R.color.salt_gray)
            )

            LazyColumn{
                itemsIndexed(products) { index, product ->
                    val qty = quantities[product.id] ?: 0

                    ProductItemComponent(
                        product.title,
                        product.printedPrice,
                        product.stock,
                        qty,
                        onPlusClicked = { onPlusClicked(product.id, product.stock, product.price) },
                        onMinusClicked = { onMinusClicked(product.id, product.price) }
                    )

                    if (index == products.lastIndex) {
                        Spacer(Modifier.height(32.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductItemComponent(
    title: String,
    price: String,
    stock: Int,
    qty: Int,
    onPlusClicked: () -> Unit,
    onMinusClicked: () -> Unit
) {

    Row(
        Modifier
            .padding(top = 42.dp)
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                title,
                fontFamily = FiraSans,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(R.color.salt_dark),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                price,
                fontFamily = FiraSans,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = colorResource(R.color.salt_dark_ice),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    colorResource(R.color.salt_gray),
                    RoundedCornerShape(8.dp),
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onMinusClicked()
                }
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            if (qty > 0)
                                colorResource(R.color.salt_button_activated)
                            else
                                colorResource(R.color.salt_button_deactivated),
                            RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.minus),
                        null,
                        tint = colorResource(R.color.white)
                    )
                }
            }

            Box(
                Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "$qty",
                    fontFamily = FiraSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    color = colorResource(R.color.salt_dark_hot),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            IconButton(
                onClick = {
                    onPlusClicked()
                }
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            if (qty < stock)
                                colorResource(R.color.salt_button_activated)
                            else
                                colorResource(R.color.salt_button_deactivated),
                            RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.plus),
                        null,
                        tint = colorResource(R.color.white)
                    )
                }
            }
        }
    }
}