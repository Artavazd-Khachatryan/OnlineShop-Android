package com.onlineshop.online_shop

import androidx.lifecycle.ViewModel
import com.onlineshop.online_shop.data.dtomodels.ProductDTO
import com.onlineshop.onlineshopkmmlibrary.useCases.LoadAllProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel(
    private val loadAllProductsUseCase: LoadAllProductsUseCase
) : ViewModel() {

    private val _productsMutableFlow = MutableStateFlow(emptyList<ProductDTO>())
    val productsFlow = _productsMutableFlow.asStateFlow()
    var shopId: Long = -1

    init {
        loadProducts(shopId)
    }

    private fun loadProducts(shopId: Long) {
        if (shopId == -1L) return

        loadAllProductsUseCase.apply {

            onSuccess = { productEntities ->
                val productsDTO = productEntities.map { productEntity ->
                    ProductDTO(
                        id = productEntity.id,
                        shopId = productEntity.shop,
                        title = productEntity.title,
                        description = productEntity.description,
                        price = productEntity.price,
                        category = productEntity.category
                    )
                }

                _productsMutableFlow.value = productsDTO
            }

            onFail = {

            }

            execute(shopId)
        }
    }
}