package com.onlineshop.online_shop

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onlineshop.online_shop.data.dtomodels.ProductDTO
import com.onlineshop.online_shop.data.dtomodels.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    private val _productsMutableFlow = MutableStateFlow(emptyList<ProductDTO>())
    val productsFlow = _productsMutableFlow.asStateFlow()

    private val shopIdFlow = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            shopIdFlow.collectLatest { shopId ->
                shopId?.let {
                    loadProducts(it)
                }
            }
        }
    }

    fun onShopId(shopId: String) {
        shopIdFlow.value = shopId
    }

    private fun loadProducts(shopId: String) {
        val products = productRepository.getProductWithId(shopId)
        _productsMutableFlow.value = products
    }

    object ProductsScreen {
        private const val ARG_SHOP_ID = "shopId"
        const val PATH = "{$ARG_SHOP_ID}/products"

        fun getShopId(arg: Bundle): String =
            arg.getString(ARG_SHOP_ID) ?: throw NotFoundException("Argument shop id not found")

        fun navigationPath(shopId: String): String = PATH.replace("{$ARG_SHOP_ID}", shopId)
    }
}