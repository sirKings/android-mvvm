package co.king.lloydsapp.currencyList.data.repository

import co.king.lloydsapp.currencyList.data.local.CurrencyDatabase
import co.king.lloydsapp.currencyList.data.mappers.toCurrency
import co.king.lloydsapp.currencyList.data.remote.CurrencyApi
import co.king.lloydsapp.currencyList.domain.model.Currency
import co.king.lloydsapp.currencyList.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyApi,
    private val database: CurrencyDatabase
): CurrencyRepository {
    override fun fetchCurrencies(): Flow<List<Currency>> {
        return flow {

        }
    }

}