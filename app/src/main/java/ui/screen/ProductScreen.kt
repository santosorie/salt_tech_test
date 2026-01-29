package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.salttech.R
import com.example.salttech.ui.theme.FiraSans
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.salttech.ui.component.ButtonStyle
import com.example.salttech.ui.component.PriceBottomBarComponent
import com.example.salttech.ui.component.ProductListComponent
import com.example.salttech.ui.component.RoundedButtonComponent
import com.example.salttech.ui.component.RoundedTopBarComponent
import com.example.salttech.ui.screen.ProductUiState
import com.example.salttech.ui.screen.ProductViewModel
import com.example.salttech.ui.screen.SortOption
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.salttech.ui.component.CheckoutDialogComponent
import com.example.salttech.ui.component.SortDialogComponent

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = viewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    var showCheckoutDialog by remember { mutableStateOf(false) }
    var showSortDialog by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Default") }

    var dialogText = ""

    Scaffold(
        topBar = {
            val productCount = (state as? ProductUiState.Success)
                ?.products
                ?.size
                ?: 0

            RoundedTopBarComponent(if (productCount > 0) "$productCount products" else "")
        },
        bottomBar = {
            if (state is ProductUiState.Success) {
                val price = viewModel.price.collectAsStateWithLifecycle().value

                val enableCheckoutButton = price > 0.0
                val rupiahS =  "Rp " + "%,d".format(price.toLong()).replace(',', '.')

                PriceBottomBarComponent(
                    rupiahS,
                    enableCheckoutButton,
                    {
                        dialogText = "You have successfully purchase ${viewModel.totalItemInCart()}\n" +
                                " products with total of $rupiahS.\n" +
                                " Click close to buy another modems"
                        showCheckoutDialog = true
                    },
                    { viewModel.reset() }
                )
            }
        }
    ) { padding ->
        Surface(
            Modifier
                .padding(padding)
        ) {

            if (showCheckoutDialog) {
                CheckoutDialogComponent(
                    dialogText,
                    onCloseButtonClicked = {
                        viewModel.reset()
                        showCheckoutDialog = false
                    }
                )
            } else if (showSortDialog) {
                SortDialogComponent(
                    selectedOption,
                    onClickOption = { selectedOption = it },
                    onDismissRequest = { showSortDialog = false },
                    onClickApply = {
                        viewModel.sortBy(selectedOption)
                        showSortDialog = false
                    }
                )
            }

            when (state) {
                is ProductUiState.Loading -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.mobile),
                                null,
                                tint = colorResource(R.color.salt_button_activated),
                                modifier = Modifier.size(48.dp, 76.dp)
                            )

                            Text(
                                "Loading Product Data",
                                fontFamily = FiraSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = colorResource(R.color.salt_button_activated),
                                modifier = Modifier.padding(top = 28.dp)
                            )

                            Text(
                                "Please Wait...",
                                fontFamily = FiraSans,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = colorResource(R.color.salt_dark_ice),
                                modifier = Modifier.padding(top = 12.dp)
                            )
                        }
                    }
                }
                is ProductUiState.Success -> {
                    val products = state.products

                    ProductListComponent(
                        products,
                        viewModel.quantities.collectAsStateWithLifecycle().value,
                        selectedOption,
                        onPlusClicked = { productId, stock, price ->
                            viewModel.increaseQty(productId, stock, price)
                        },
                        onMinusClicked = { productId, price ->
                            viewModel.decreaseQty(productId, price)
                        },
                        onClickSortButton = {
                            showSortDialog = true
                        }
                    )
                }
                is ProductUiState.Error -> {
                    val errorMessage = state.message

                    Box(
                        Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            errorMessage,
                            fontFamily = FiraSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = colorResource(R.color.salt_red)
                        )
                    }
                }
            }

        }
    }
}
