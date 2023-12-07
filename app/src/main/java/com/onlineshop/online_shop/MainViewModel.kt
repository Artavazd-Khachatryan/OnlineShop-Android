package com.onlineshop.online_shop

import androidx.lifecycle.ViewModel
import com.onlineshop.online_shop.data.dtomodels.ProductDTO
import com.onlineshop.online_shop.data.dtomodels.ProductRepository
import com.onlineshop.online_shop.data.dtomodels.ShopDTO
import com.onlineshop.online_shop.data.dtomodels.ShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val shopRepository = ShopRepository()
    private val productRepository = ProductRepository()

    private val _shopsMutableFlow = MutableStateFlow(emptyList<ShopDTO>())
    val shopsFlow = _shopsMutableFlow.asStateFlow()

    private val _productsMutableFlow = MutableStateFlow(emptyList<ProductDTO>())
    val productsFlow = _productsMutableFlow.asStateFlow()

    init {
        _shopsMutableFlow.value = shopRepository.getShopList()
        _productsMutableFlow.value = productRepository.getProductList()
    }

    enum class Screen {
        SHOPS,
        PRODUCTS,
    }

    sealed class NavigationItem(val route: String) {
        object Shops : NavigationItem(Screen.SHOPS.name)
        object Products : NavigationItem(Screen.PRODUCTS.name)
    }
}