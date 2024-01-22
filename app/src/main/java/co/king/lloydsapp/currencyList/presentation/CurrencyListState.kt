package co.king.lloydsapp.currencyList.presentation

import co.king.lloydsapp.currencyList.domain.model.Currency

data class CurrencyListState(
    val loading: Boolean = false,
    val items: List<Currency> = emptyList(),
    val errorMessage: String = ""
)
