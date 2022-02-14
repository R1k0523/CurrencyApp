package ru.boringowl.parapp.presentation.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.CurrencyRate
import java.sql.Timestamp

@Entity(tableName = "currency_rate")
class CurrencyRateDTO(
    @PrimaryKey
    override var currencies: Pair<String, String>,
    override var rate: Float,
    override var nextUpdate: Timestamp
) : CurrencyRate(currencies, rate, nextUpdate) {
    constructor(rate: CurrencyRate) : this(
        rate.currencies,
        rate.rate,
        rate.nextUpdate
    )
}