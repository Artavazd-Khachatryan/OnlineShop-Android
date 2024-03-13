package com.onlineshop.online_shop

import android.app.Application
import com.onlineshop.online_shop.di.repositoryModule
import com.onlineshop.online_shop.di.viewModelModule
import com.onlineshop.onlineshopkmmlibrary.dependancyInjection.koinModules
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(viewModelModule, repositoryModule)
            koinModules()
        }
    }
}