package co.king.lloydsapp.currencyList.domain.repository

import co.king.lloydsapp.currencyList.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun fetchCurrencies()

    fun observeCurrencies(): Flow<List<Currency>>
}