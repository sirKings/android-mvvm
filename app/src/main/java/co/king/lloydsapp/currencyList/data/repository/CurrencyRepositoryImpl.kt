package co.king.lloydsapp.currencyList.data.repository

import co.king.lloydsapp.currencyList.data.local.CurrencyDatabase
import co.king.lloydsapp.currencyList.data.mappers.toCurrency
import co.king.lloydsapp.currencyList.data.mappers.toEntity
import co.king.lloydsapp.currencyList.data.remote.CurrencyApi
import co.king.lloydsapp.currencyList.domain.model.Currency
import co.king.lloydsapp.currencyList.domain.repository.CurrencyRepository
import co.king.lloydsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyApi,
    private val database: CurrencyDatabase
): CurrencyRepository {
    override fun fetchCurrencies(): Flow<Resource<List<Currency>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            //fetch from the local first
            val localList = database.currencyDao.observeCurrencyList()
            if(localList.isNotEmpty()){
                emit(Resource.Success(data = localList.map { entity -> entity.toCurrency() }))
            }

            //fetch from the api
            val remoteCurrencyList = try {
                api.getCurrencyList().body()
            }catch (e: Exception){
                e.printStackTrace()
                emit(Resource.Failure(data = null, errorMessage = e.localizedMessage ?: "An error occurred"))
                return@flow
            }

            val remoteExchangeRates = try {
                api.getExchangeRates().body()?.rates
            }catch (e: Exception){
                e.printStackTrace()
                emit(Resource.Failure(data = null, errorMessage = e.localizedMessage ?: "An error occurred"))
                return@flow
            }

            if (remoteCurrencyList.isNullOrEmpty() || remoteExchangeRates.isNullOrEmpty()) return@flow

            val currencies = remoteCurrencyList.mapNotNull {
                val item = remoteExchangeRates[it.key]?.let { it1 -> Currency(name = it.value, symbol = it.key, rate = it1) }
                item
            }

            database.currencyDao.saveCurrencyList(currencies.map { it.toEntity()})

            emit(Resource.Success(data = currencies))

        }
    }

}