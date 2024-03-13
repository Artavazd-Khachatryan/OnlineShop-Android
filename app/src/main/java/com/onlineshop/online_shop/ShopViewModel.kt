package com.onlineshop.online_shop

import androidx.lifecycle.ViewModel
import com.onlineshop.online_shop.data.dtomodels.ShopDTO
import com.onlineshop.onlineshopkmmlibrary.useCases.LoadAllShoppesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShopViewModel(private val loadAllShoppesUseCase: LoadAllShoppesUseCase) : ViewModel() {

    private val _shopsMutableFlow = MutableStateFlow(emptyList<ShopDTO>())
    val shopsFlow = _shopsMutableFlow.asStateFlow()

    init {
        loadShops()
    }

    private fun loadShops() {
        loadAllShoppesUseCase.apply {
            onSuccess = { shopEntities ->
                val shopsDto = shopEntities.map { shopEntity ->
                    ShopDTO(
                        id = shopEntity.id,
                        name = shopEntity.name,
                        description = shopEntity.description
                    )
                }

                _shopsMutableFlow.value = shopsDto
            }
            onFail = {

            }
        }.execute()
    }
}