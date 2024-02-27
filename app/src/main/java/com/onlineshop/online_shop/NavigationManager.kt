package com.onlineshop.online_shop

import android.content.res.Resources
import android.os.Bundle

class NavigationManager {

    object ProductInformation {
        private const val ARG_PRODUCT_ID = "product_id"
        val PATH = "product_details/{$ARG_PRODUCT_ID}"

        fun getProductId(arg: Bundle): Long =
            arg.getString(ARG_PRODUCT_ID)?.toLong()
                ?: throw Resources.NotFoundException("Argument shop id not found")

        fun navigationPath(productId: Long): String =
            PATH.replace("{$ARG_PRODUCT_ID}", "$productId")
    }

    object ProductsScreen {
        const val ARG_SHOP_ID = "shopId"
        const val PATH = "{$ARG_SHOP_ID}/products"

        fun getShopId(arg: Bundle): Long =
            arg.getString(ARG_SHOP_ID)?.toLong()
                ?: throw Resources.NotFoundException("Argument shop id not found")

        fun navigationPath(shopId: Long): String = PATH.replace("{$ARG_SHOP_ID}", "$shopId")
    }

    object ShopsScreen {
        const val PATH = "shops"
    }

    object LoginScreen {
        const val PATH = "login"
    }

    object RegisterScreen {
        const val PATH = "register"
    }
}