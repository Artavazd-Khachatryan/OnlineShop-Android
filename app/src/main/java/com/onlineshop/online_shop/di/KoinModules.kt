package com.onlineshop.online_shop.di

import com.onlineshop.online_shop.ProductViewModel
import com.onlineshop.online_shop.ShopViewModel
import com.onlineshop.online_shop.data.dtomodels.ProductRepository
import com.onlineshop.online_shop.data.dtomodels.ShopRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// TODO need to create test for modules

val viewModelModule = module {
    viewModel<ShopViewModel> { ShopViewModel(get()) }
    viewModel<ProductViewModel> {ProductViewModel(get())}
}

val repositoryModule = module {
    single<ShopRepository> { ShopRepository() }
    factory<ProductRepository> { ProductRepository() }
}

