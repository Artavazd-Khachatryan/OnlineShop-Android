package com.onlineshop.online_shop

import android.app.Application
import com.onlineshop.online_shop.di.repositoryModule
import com.onlineshop.online_shop.di.viewModelModule
import com.onlineshop.onlineshopkmmlibrary.dependancyInjection.shopLibraryKoinModules
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(viewModelModule, repositoryModule)
            shopLibraryKoinModules()
        }
    }
}