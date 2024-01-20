package co.king.lloydsapp.currencyList.data.remote.dto

data class ExchangeRateResponse(
    val amount: Int,
    val base: String,
    val date: String,
    val rates: Map<String, String>
)
