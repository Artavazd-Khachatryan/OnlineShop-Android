package com.onlineshop.online_shop.data.dtomodels

data class ProductDTO(
    val id: Long? = null,
    val shopId: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val price: Double,
    val category: String? = null
)
