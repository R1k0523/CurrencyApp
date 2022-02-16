package ru.boringowl.parapp.presentation.repository.network.currency

import ru.boringowl.parapp.domain.model.CurrencyRate
import java.sql.Timestamp

data class CurrencyResponse(
    val time_next_update_unix: Long,
    val base_code: String,
    val conversion_rates: HashMap<String, Float>,
) {
    fun toList(): List<CurrencyRate> {
        val nextUpdate = Timestamp(time_next_update_unix)
        val rates = conversion_rates.map {
            CurrencyRate(
                Pair(
                    base_code,
                    it.key
                ), it.value, nextUpdate
            )
        }
        return rates
    }

    constructor() : this(
        0L,
        "",
        hashMapOf()
    )
}