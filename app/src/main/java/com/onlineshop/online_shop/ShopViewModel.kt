package com.onlineshop.online_shop

import androidx.lifecycle.ViewModel
import com.onlineshop.online_shop.data.dtomodels.ShopDTO
import com.onlineshop.online_shop.data.dtomodels.ShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShopViewModel(shopRepository: ShopRepository) : ViewModel() {

    private val _shopsMutableFlow = MutableStateFlow(emptyList<ShopDTO>())
    val shopsFlow = _shopsMutableFlow.asStateFlow()

    init {
        _shopsMutableFlow.value = shopRepository.getShopList()
    }
}