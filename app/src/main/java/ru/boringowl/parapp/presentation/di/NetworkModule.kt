package ru.boringowl.parapp.presentation.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.boringowl.parapp.BuildConfig
import ru.boringowl.parapp.presentation.repository.network.currency.CurrencyApi
import ru.boringowl.parapp.presentation.repository.network.currency.CurrencyService


val networkModule = module {
    single { okhttpClient() }
    single { retrofit(get(), BuildConfig.BASE_URL) }
    single { apiCurrency(get()) }
    single { currencyService(get()) }
}

fun okhttpClient() : OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}

fun retrofit(okHttpClient: OkHttpClient, url: String) : Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun apiCurrency(retrofit: Retrofit) : CurrencyApi = retrofit.create(CurrencyApi::class.java)

fun currencyService(api: CurrencyApi) : CurrencyService = CurrencyService(api)

