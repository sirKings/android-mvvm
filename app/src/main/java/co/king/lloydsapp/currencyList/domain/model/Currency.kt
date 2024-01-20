package co.king.lloydsapp.currencyList.domain.model


data class Currency(
    val id: Long,
    val name: String,
    val symbol: String,
    val rate: Double
)
