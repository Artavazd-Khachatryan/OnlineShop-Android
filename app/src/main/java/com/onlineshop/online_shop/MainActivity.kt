package com.onlineshop.online_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onlineshop.online_shop.data.dtomodels.ProductDTO
import com.onlineshop.online_shop.data.dtomodels.ProductRepository
import com.onlineshop.online_shop.data.dtomodels.ShopDTO
import com.onlineshop.online_shop.data.dtomodels.ShopRepository

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavHost(
                    navController = rememberNavController(),
                    NavigationManager.ShopsScreen.PATH
                )
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationManager.ShopsScreen.PATH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationManager.ShopsScreen.PATH) {
            val shopViewModel: ShopViewModel = viewModel(factory = viewModelFactory {
                addInitializer(ShopViewModel::class) {
                    ShopViewModel(ShopRepository())
                }
            })
            val shops = shopViewModel.shopsFlow.collectAsState()
            ShopsScreen(
                shops.value,
                onItemClick = { shopId ->
                    navController.navigate(
                        NavigationManager.ProductsScreen.navigationPath(shopId)
                    )
                }
            )
        }

        composable(NavigationManager.ProductsScreen.PATH) { backStackEntry ->

            val shopId = NavigationManager.ProductsScreen.getShopId(backStackEntry.arguments!!)
            val productViewModel: ProductViewModel = viewModel(
                factory = viewModelFactory {
                    addInitializer(ProductViewModel::class) {
                        ProductViewModel(ProductRepository(), shopId)
                    }
                }
            )

            val products = productViewModel.productsFlow.collectAsState()
            ProductScreen(
                products.value,
                onItemClick = { productId ->
                    navController.navigate(
                        NavigationManager.ProductInformation.navigationPath(productId)
                    )
                }
            )
            // TODO navigate to ProductInformation screen after onClick
        }
        composable(NavigationManager.ProductInformation.PATH) { backStackEntry ->
            val productId =
                NavigationManager.ProductInformation.getProductId(backStackEntry.arguments!!)

            val productInformationViewModel: ProductInformationViewModel = viewModel(
                factory = viewModelFactory {
                    addInitializer(ProductInformationViewModel::class) {
                        ProductInformationViewModel(
                            ProductRepository(),
                            productId
                        )
                    }
                })

            val product = productInformationViewModel.productFlow.collectAsState()
            product.value?.let { ProductInformationScreen(product = it) }
        }
    }
}

@Composable
fun ShopsScreen(shops: List<ShopDTO>, onItemClick: (Long) -> Unit) {
    LazyColumn {
        items(shops) { shopDTO ->
            shopDTO.name?.let {
                ClickableText(
                    modifier = Modifier.fillMaxSize(),
                    text = AnnotatedString(it),
                    onClick = { onItemClick(shopDTO.id) })
            }
        }
    }
}

@Composable
fun ProductScreen(products: List<ProductDTO>, onItemClick: (Long) -> Unit) {
    LazyColumn {
        items(products) { product ->
            product.title?.let {
                ClickableText(
                    modifier = Modifier.fillMaxSize(),
                    text = AnnotatedString(it),
                    onClick = { onItemClick(product.id) }
                )
            }
        }
    }
}

@Composable
fun ProductInformationScreen(product: ProductDTO) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = AnnotatedString(product.price.toString())
        )

        Text(
            text = AnnotatedString(product.category ?: "")
        )

        Text(
            text = AnnotatedString(product.description ?: "")
        )
    }
}
