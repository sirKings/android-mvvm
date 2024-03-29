package co.king.lloydsapp.currencyList.data.remote

import co.king.lloydsapp.currencyList.data.remote.dto.ExchangeRateResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {

    @GET("currencies")
    suspend fun getCurrencyList(): Response<Map<String, String>>

    @GET("latest")
    suspend fun getExchangeRates(): Response<ExchangeRateResponse>
}