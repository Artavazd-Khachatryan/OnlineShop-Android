package com.onlineshop.online_shop.ui

import androidx.lifecycle.ViewModel
import com.onlineshop.online_shop.data.dtomodels.ShopDTO
import com.onlineshop.online_shop.data.dtomodels.ShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShopViewModel : ViewModel() {

    private val shopRepository = ShopRepository()

    private val _shopsMutableFlow = MutableStateFlow(emptyList<ShopDTO>())
    val shopsFlow = _shopsMutableFlow.asStateFlow()

    init {
        _shopsMutableFlow.value = shopRepository.getShopList()
    }

    object ShopsScreen {
        const val PATH = "shops"
    }
}