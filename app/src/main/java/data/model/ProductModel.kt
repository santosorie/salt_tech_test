package com.example.salttech.data.model

data class ProductModel(
    val id: Int,
    val title: String,
    val price: Double,
    val stock: Int
) {

}

data class ProductResponse(
    val products: List<ProductModel>
)