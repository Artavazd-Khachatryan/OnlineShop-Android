package com.onlineshop.online_shop

import androidx.lifecycle.ViewModel
import com.onlineshop.online_shop.data.dtomodels.ProductDTO
import com.onlineshop.online_shop.data.dtomodels.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.properties.Delegates

class ProductViewModel(private val productRepository: ProductRepository,shopId: Long) : ViewModel() {

    private val _productsMutableFlow = MutableStateFlow(emptyList<ProductDTO>())
    val productsFlow = _productsMutableFlow.asStateFlow()

    init {
        loadProducts(shopId)
    }

    private fun loadProducts(shopId: Long) {
        if (shopId == -1L) return

        val products = productRepository.getProductWithId(shopId)
        _productsMutableFlow.value = products
    }
}