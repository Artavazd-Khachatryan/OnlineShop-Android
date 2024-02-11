package com.onlineshop.online_shop.data.dtomodels

data class ProductDTO(
    val id: Long,
    val shopId: Long,
    val title: String? = null,
    val description: String? = null,
    val price: Double,
    val category: String? = null
)
