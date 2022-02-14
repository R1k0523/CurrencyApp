package ru.boringowl.parapp.domain.model

import java.sql.Timestamp

open class CurrencyRate(
    open val currencies: Pair<String, String>,
    open val rate: Float,
    open val nextUpdate: Timestamp
)