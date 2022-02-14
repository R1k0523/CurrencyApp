package ru.boringowl.parapp.presentation.repository.network.currency

import ru.boringowl.parapp.presentation.repository.network.BaseService
import ru.boringowl.parapp.presentation.repository.network.MyResult

class CurrencyService(private val api: CurrencyApi) : BaseService() {
    suspend fun getCurrencies(currency: String): MyResult<CurrencyResponse> =
        createCall { api.getRate(currency) }
}