package com.onlineshop.online_shop.data.dtomodels

import androidx.compose.runtime.produceState

class ProductRepository {

    fun getProductList(): List<ProductDTO> {

        val productList = mutableListOf<ProductDTO>()
        productList.add(ProductDTO(id = 1, shopId = 1,"Samsung",null,600.0,"Android smartphone"))
        productList.add(ProductDTO(id = 2, shopId = 2,"Casio",null,300.0,"Time"))
        productList.add(ProductDTO(id = 3, shopId = 3,"Timex",null,100.0,"Time"))
        productList.add(ProductDTO(id = 4, shopId = 4,"Fossil",null,300.0,"Time"))
        productList.add(ProductDTO(id = 5, shopId = 5,"Dell",null,1300.0,"Laptop"))
        productList.add(ProductDTO(id = 6, shopId = 6,"HP",null,1000.0,"Laptop"))
        productList.add(ProductDTO(id = 7, shopId = 7,"Apple",null,1500.0,"Laptop"))
        productList.add(ProductDTO(id = 8, shopId = 8,"Iphone",null,300.0,"Ios smartphone"))
        return productList
    }
}