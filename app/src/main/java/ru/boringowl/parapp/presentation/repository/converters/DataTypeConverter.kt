package ru.boringowl.parapp.presentation.repository.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import org.koin.java.KoinJavaComponent.inject
import java.lang.reflect.Type
import java.sql.Timestamp


object DataTypeConverter {
    private val gson by inject(Gson::class.java)

    @TypeConverter
    fun stringToPair(pair: String): Pair<String, String> {
        val pairType: Type = object : TypeToken<Pair<String, String>>() {}.type
        return gson.fromJson(pair, pairType)
    }
    @TypeConverter
    fun pairToString(pair: Pair<String, String>): String {
        return gson.toJson(pair)
    }

    @TypeConverter
    fun stringToTimestamp(ts: String): Timestamp {
        return gson.fromJson(ts, Timestamp::class.java)
    }

    @TypeConverter
    fun timestampToString(ts: Timestamp): String {
        return gson.toJson(ts)
    }
}
