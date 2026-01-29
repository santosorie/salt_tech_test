package com.example.salttech.ui.screen

import com.example.salttech.data.model.ProductModel

data class ProductUiModel(
    val id: Int,
    val title: String,
    val price: Double,
    val stock: Int,
    val printedPrice: String
) {
    companion object {
        fun from(model: ProductModel): ProductUiModel {
            val usdToIdrMultiplier = 16782.5

            val rupiah = model.price * usdToIdrMultiplier
            val rupiahS =  "Rp " + "%,d".format(rupiah.toLong()).replace(',', '.')

            return ProductUiModel(
                id = model.id,
                title = model.title,
                price = rupiah,
                stock = model.stock,
                printedPrice = rupiahS
            )
        }
    }
}