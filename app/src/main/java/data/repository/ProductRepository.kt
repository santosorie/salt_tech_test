package com.example.salttech.data.repository

import com.example.salttech.data.model.ProductModel
import com.example.salttech.data.network.ApiClient


class ProductRepository {
    suspend fun fetchProducts(): List<ProductModel> {
        val products = ApiClient.api.getProducts().products
        return products
    }
}