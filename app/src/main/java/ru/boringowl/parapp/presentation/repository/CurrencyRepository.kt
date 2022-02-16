package ru.boringowl.parapp.presentation.repository

import androidx.lifecycle.LiveData
import ru.boringowl.parapp.domain.model.CurrencyRate

interface CurrencyRepository {
    fun <T : CurrencyRate> getAllRates(): LiveData<List<T>>
    suspend fun <T : CurrencyRate> addRate(rate: T)
    fun <T : CurrencyRate> getRate(
        pair: Pair<String, String>,
        onError: (message: String) -> Unit
    ): LiveData<T?>
}
