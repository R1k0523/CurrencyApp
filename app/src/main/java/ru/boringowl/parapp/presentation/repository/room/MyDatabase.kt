package ru.boringowl.parapp.presentation.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.boringowl.parapp.presentation.repository.converters.DataTypeConverter
import ru.boringowl.parapp.presentation.repository.model.CurrencyRateDTO
import ru.boringowl.parapp.presentation.repository.room.dao.CurrencyDAO

@Database(entities = [CurrencyRateDTO::class],
    version = 1, exportSchema = false)
@TypeConverters(DataTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun currencyRateDAO(): CurrencyDAO
}
