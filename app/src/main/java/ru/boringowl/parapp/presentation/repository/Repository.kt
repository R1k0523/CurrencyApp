package ru.boringowl.parapp.presentation.repository

import org.koin.java.KoinJavaComponent.inject

object Repository {
        val currencyRepository: CurrencyRepository by inject(CurrencyRepository::class.java)
}