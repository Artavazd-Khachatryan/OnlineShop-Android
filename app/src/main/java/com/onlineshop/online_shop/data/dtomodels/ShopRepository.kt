package com.onlineshop.online_shop.data.dtomodels

class ShopRepository {

    fun getShopList(): List<ShopDTO> {
        val shopList = mutableListOf<ShopDTO>()
        shopList.add(ShopDTO(id = "1", name = "Phones", "Smartphone"))
        shopList.add(ShopDTO(id = "2", name = "Times", "Time"))
        shopList.add(ShopDTO(id = "3", name = "Cloths", "Cloth"))
        shopList.add(ShopDTO(id = "4", name = "Bijouterie", "Bijouterie"))
        shopList.add(ShopDTO(id = "5", name = "Accessories", "Accessories"))
        shopList.add(ShopDTO(id = "6", name = "Electronics", "Electronics"))
        return shopList
    }
}