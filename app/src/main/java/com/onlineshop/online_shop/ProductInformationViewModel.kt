package com.onlineshop.online_shop

import androidx.lifecycle.ViewModel
import com.onlineshop.online_shop.data.dtomodels.ProductDTO
import com.onlineshop.online_shop.data.dtomodels.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductInformationViewModel(private val productRepository: ProductRepository, private val productId: Long): ViewModel() {

    private val _productMutableFlow = MutableStateFlow<ProductDTO?>(null)
    val productFlow = _productMutableFlow.asStateFlow()

    init {
        loadProduct(productId)
    }

    private fun loadProduct(productId: Long) {
        val product = productRepository.getProduct(productId)
        _productMutableFlow.value = product
    }
}