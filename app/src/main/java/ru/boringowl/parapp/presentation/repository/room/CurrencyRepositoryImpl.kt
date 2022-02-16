package ru.boringowl.parapp.presentation.repository.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import org.koin.java.KoinJavaComponent.inject
import ru.boringowl.parapp.domain.model.CurrencyRate
import ru.boringowl.parapp.presentation.repository.CurrencyRepository
import ru.boringowl.parapp.presentation.repository.model.CurrencyRateDTO
import ru.boringowl.parapp.presentation.repository.network.MyResult
import ru.boringowl.parapp.presentation.repository.network.currency.CurrencyService
import ru.boringowl.parapp.presentation.repository.room.dao.CurrencyDAO
import java.sql.Timestamp

class CurrencyRepositoryImpl : CurrencyRepository {
    private var currencyDAO: CurrencyDAO
    private val currencyService by inject(CurrencyService::class.java)
    private var allRates: LiveData<List<CurrencyRate>>

    init {
        val db: MyDatabase by inject(MyDatabase::class.java)
        currencyDAO = db.currencyRateDAO()
        allRates = currencyDAO.getAllRates() as LiveData<List<CurrencyRate>>
    }

    override fun <T : CurrencyRate> getAllRates(): LiveData<List<T>> = allRates as LiveData<List<T>>
    override suspend fun <T : CurrencyRate> addRate(rate: T) =
        currencyDAO.addRate(CurrencyRateDTO(rate))

    override fun <T : CurrencyRate> getRate(
        pair: Pair<String, String>,
        onError: (message: String) -> Unit
    ): LiveData<T?> {
        val currency: LiveData<CurrencyRate?> = liveData {
            try {
                val local = currencyDAO.getRateSync(pair)
                if (local == null ||
                    local.nextUpdate.before(Timestamp(System.currentTimeMillis() / 1000))
                )
                    when (val netCurrency = currencyService.getCurrencies(pair.first)) {
                        is MyResult.Success -> netCurrency.data.toList()
                            .forEach { currencyDAO.addRate(CurrencyRateDTO(it)) }
                        is MyResult.Error -> {
                            Log.e("Network Error", netCurrency.error.toString())
                            onError(netCurrency.error.message.toString())
                        }
                    }
                emitSource(currencyDAO.getRate(pair) as LiveData<CurrencyRate?>)
            } catch (exception: Exception) {
                emitSource(currencyDAO.getRate(pair) as LiveData<CurrencyRate?>)
            }
        }
        return currency as LiveData<T?>
    }
}