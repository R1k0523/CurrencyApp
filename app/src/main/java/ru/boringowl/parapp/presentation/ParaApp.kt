package ru.boringowl.parapp.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.boringowl.parapp.presentation.di.networkModule
import ru.boringowl.parapp.presentation.di.repositoryModule

class CurrencyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CurrencyApp)
            modules(listOf(repositoryModule, networkModule))
        }
    }
}