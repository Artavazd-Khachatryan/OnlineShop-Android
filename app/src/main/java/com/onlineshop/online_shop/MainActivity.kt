package com.onlineshop.online_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onlineshop.online_shop.data.dtomodels.ProductDTO
import com.onlineshop.online_shop.data.dtomodels.ShopDTO
import com.onlineshop.online_shop.ui.theme.OnlineShopTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavHost(
                    mainViewModel,
                    navController = rememberNavController(),
                    MainViewModel.NavigationItem.Shops.route
                )
            }
        }
    }
}


@Composable
fun AppNavHost(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    startDestination: String = MainViewModel.NavigationItem.Shops.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainViewModel.NavigationItem.Shops.route) {
            val shops = mainViewModel.shopsFlow.collectAsState()
            ShopsScreen(shops.value) {
                navController.navigate(MainViewModel.Screen.PRODUCTS.name)
            }
        }
        composable(MainViewModel.NavigationItem.Products.route) {
            val products = mainViewModel.productsFlow.collectAsState()
            ProductScreen(products.value)
        }
    }
}

@Composable
fun ShopsScreen(shops: List<ShopDTO>, onItemClick: (Long) -> Unit) {
    LazyColumn {
        shops.forEach { shopDTO ->
            item {
                shopDTO.name?.let {
                    ClickableText(
                        modifier = Modifier.fillMaxSize(),
                        text = AnnotatedString(it),
                        onClick = { onItemClick(shopDTO.id!!) })
                }
            }
        }
    }
}

@Composable
fun ProductScreen(product: List<ProductDTO>) {
    LazyColumn {
        product.forEach { product ->
            item {
                product.title?.let {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = AnnotatedString(it)
                    )
                }
            }
        }
    }
}

@Composable
fun TestView(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OnlineShopTheme {
        TestView("Android")
    }
}