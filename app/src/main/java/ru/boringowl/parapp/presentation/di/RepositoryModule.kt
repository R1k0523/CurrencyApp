package ru.boringowl.parapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.boringowl.parapp.presentation.repository.*
import ru.boringowl.parapp.presentation.repository.room.*

val repositoryModule = module {
    single { provideGson() }
    single { provideCurrencyRepository() }
    single { provideDatabase(this.androidContext()) }
}

fun provideCurrencyRepository() : CurrencyRepository = CurrencyRepositoryImpl()
fun provideGson(): Gson = Gson()

fun provideDatabase(context: Context): MyDatabase =
    Room.databaseBuilder(
        context.applicationContext,
        MyDatabase::class.java,
        "currency_db1"
    )
    .fallbackToDestructiveMigration()
    .allowMainThreadQueries()
    .build()
