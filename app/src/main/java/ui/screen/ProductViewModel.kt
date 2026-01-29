package com.example.salttech.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salttech.data.model.ProductModel
import com.example.salttech.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ProductUiState {
    object Loading: ProductUiState()
    data class Success(val products: List<ProductUiModel>): ProductUiState()
    data class Error(val message: String): ProductUiState()
}

enum class SortOption(val value: String) {
    DEFAULT("Default"),
    HIGHEST_PRICE("Highest Price"),
    LOWEST_PRICE("Lowest Price"),
    NAME_A_Z("Name (a-z)");

    companion object {
        fun fromValue(label: String): SortOption {
            return entries.firstOrNull { it.value == label }
                ?: DEFAULT
        }
    }
}

class ProductViewModel: ViewModel() {
    private val repository = ProductRepository()

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _quantities = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val quantities = _quantities.asStateFlow()

    private val _price = MutableStateFlow<Double>(0.0)
    val price = _price.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                val products = repository.fetchProducts().map {
                    ProductUiModel.from(it)
                }
                _uiState.value = ProductUiState.Success(products)
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error(e.message.toString())
            }
        }
    }

    fun increaseQty(id: Int, stock: Int, price: Double) {
        val current = _quantities.value[id] ?: 0
        if (current < stock) {
            _quantities.value = _quantities.value + (id to (current + 1))
            _price.value += price
        }
    }

    fun decreaseQty(id: Int, price: Double) {
        val current = _quantities.value[id] ?: 0
        if (current > 0) {
            _quantities.value = _quantities.value + (id to (current - 1))
            _price.value -= price

            if (_price.value < 0) _price.value = 0.0
        }
    }

    fun reset() {
        _price.value = 0.0
        _quantities.value = emptyMap()
    }

    fun totalItemInCart(): Int {
        return _quantities.value.values.sum()
    }

    fun sortBy(label: String) {
        val products = (_uiState.value as? ProductUiState.Success)?.products ?: return
        val option = SortOption.fromValue(label)
        val sortedProducts = when (option) {
            SortOption.DEFAULT -> products.sortedBy { it.id }
            SortOption.LOWEST_PRICE -> products.sortedBy { it.price }
            SortOption.HIGHEST_PRICE -> products.sortedByDescending { it.price }
            SortOption.NAME_A_Z -> products.sortedBy { it.title }
        }

        _uiState.value = ProductUiState.Success(sortedProducts)
    }
}