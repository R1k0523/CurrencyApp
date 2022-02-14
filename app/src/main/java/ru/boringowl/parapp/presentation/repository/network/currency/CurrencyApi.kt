package ru.boringowl.parapp.presentation.repository.network.currency

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.boringowl.parapp.BuildConfig.API_KEY

interface CurrencyApi {
    @GET("v6/{token}/latest/{currency}")
    suspend fun getRate(@Path("currency") currency: String, @Path("token") token: String = API_KEY): Response<CurrencyResponse>
}