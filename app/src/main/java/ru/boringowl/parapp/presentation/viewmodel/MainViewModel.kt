package ru.boringowl.parapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.CurrencyRate
import ru.boringowl.parapp.presentation.repository.CurrencyRepository
import java.math.BigDecimal

class MainViewModel : ViewModel() {
    val repo by inject(CurrencyRepository::class.java)
    val from = MutableLiveData(BigDecimal.ZERO)
    val to = MutableLiveData(BigDecimal.ZERO)
    fun getRates(from: String, to: String, onError: (message: String) -> Unit) = repo.getRate<CurrencyRate>(Pair(from, to), onError)
}