package com.onlineshop.online_shop.data.dtomodels


class ProductRepository {

    val productList = buildList {
        add(ProductDTO(id = 1, shopId = "1", "Samsung", null, 600.0, "Android smartphone"))
        add(ProductDTO(id = 2, shopId = "2", "Casio", null, 300.0, "Time"))
        add(ProductDTO(id = 3, shopId = "2", "Timex", null, 100.0, "Time"))
        add(ProductDTO(id = 4, shopId = "2", "Fossil", null, 300.0, "Time"))
        add(ProductDTO(id = 5, shopId = "5", "Dell", null, 1300.0, "Laptop"))
        add(ProductDTO(id = 6, shopId = "5", "HP", null, 1000.0, "Laptop"))
        add(ProductDTO(id = 7, shopId = "5", "Macbook", null, 1500.0, "Laptop"))
        add(ProductDTO(id = 8, shopId = "1", "Iphone", null, 300.0, "Ios smartphone"))
    }

    fun getProductWithId(shopId: String): List<ProductDTO> {
        return productList.filter { product -> shopId == product.shopId }
    }
}