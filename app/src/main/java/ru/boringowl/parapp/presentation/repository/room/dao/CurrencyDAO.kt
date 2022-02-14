package ru.boringowl.parapp.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.*
import ru.boringowl.parapp.domain.model.CurrencyRate
import ru.boringowl.parapp.presentation.repository.model.CurrencyRateDTO

@Dao
interface CurrencyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRate(currencyRate: CurrencyRateDTO)

    @Query("SELECT * FROM currency_rate WHERE currencies = :pair")
    fun getRate(pair: Pair<String, String>): LiveData<CurrencyRateDTO?>

    @Query("SELECT * FROM currency_rate WHERE currencies = :pair")
    fun getRateSync(pair: Pair<String, String>): CurrencyRateDTO?

    @Query("SELECT * FROM currency_rate")
    fun getAllRates(): LiveData<List<CurrencyRateDTO>>
}