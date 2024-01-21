package co.king.lloydsapp.currencyList.domain.repository

import co.king.lloydsapp.currencyList.domain.model.Currency
import co.king.lloydsapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun fetchCurrencies(): Flow<Resource<List<Currency>>>

}