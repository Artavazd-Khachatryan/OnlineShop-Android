package com.onlineshop.online_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onlineshop.online_shop.data.dtomodels.ProductDTO
import com.onlineshop.online_shop.data.dtomodels.ShopDTO
import com.onlineshop.online_shop.ui.ShopViewModel

class MainActivity : ComponentActivity() {

    private val shopViewModel: ShopViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavHost(
                    shopViewModel,
                    productViewModel,
                    navController = rememberNavController(),
                    ShopViewModel.ShopsScreen.PATH
                )
            }
        }
    }
}


@Composable
fun AppNavHost(
    shopViewModel: ShopViewModel,
    productViewModel: ProductViewModel,
    navController: NavHostController,
    startDestination: String = ShopViewModel.ShopsScreen.PATH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ShopViewModel.ShopsScreen.PATH) {
            val shops = shopViewModel.shopsFlow.collectAsState()
            ShopsScreen(
                shops.value,
                onItemClick = { shopId ->
                    navController.navigate(
                        ProductViewModel.ProductsScreen.navigationPath(shopId)
                    )
                }
            )
        }
        composable(ProductViewModel.ProductsScreen.PATH) { backStackEntry ->
            val shopId = ProductViewModel.ProductsScreen.getShopId(backStackEntry.arguments!!)
            productViewModel.onShopId(shopId)
            val products = productViewModel.productsFlow.collectAsState()
            ProductScreen(products.value)
        }
    }
}

@Composable
fun ShopsScreen(shops: List<ShopDTO>, onItemClick: (String) -> Unit) {
    LazyColumn {
        items(shops) { shopDTO ->
            shopDTO.name?.let {
                ClickableText(
                    modifier = Modifier.fillMaxSize(),
                    text = AnnotatedString(it),
                    onClick = { onItemClick(shopDTO.id!!) })
            }
        }
    }
}

@Composable
fun ProductScreen(products: List<ProductDTO>) {
    LazyColumn {
        items(products) { product ->
            product.title?.let {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = AnnotatedString(it)
                )
            }
        }
    }
}