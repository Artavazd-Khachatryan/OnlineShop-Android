package com.onlineshop.online_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onlineshop.online_shop.data.dtomodels.ProductRepository
import com.onlineshop.online_shop.data.dtomodels.ShopRepository
import com.onlineshop.online_shop.ui.screens.LoginScreen
import com.onlineshop.online_shop.ui.screens.ProductInformationScreen
import com.onlineshop.online_shop.ui.screens.ProductScreen
import com.onlineshop.online_shop.ui.screens.RegisterScreen
import com.onlineshop.online_shop.ui.screens.ShopsScreen

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
                    NavigationManager.LoginScreen.PATH
                )
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
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

        composable(NavigationManager.LoginScreen.PATH) {
            LoginScreen(
                onLoginClick = {},
                onRegisterClick = { navController.navigate(NavigationManager.RegisterScreen.PATH) }
            )
        }

        composable(NavigationManager.RegisterScreen.PATH) {
            RegisterScreen()
        }
    }
}

