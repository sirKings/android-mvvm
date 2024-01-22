package co.king.lloydsapp.currencyList.data.repository


import co.king.lloydsapp.currencyList.data.local.CurrencyDao
import co.king.lloydsapp.currencyList.data.local.entities.CurrencyEntity
import co.king.lloydsapp.currencyList.data.remote.CurrencyApi
import co.king.lloydsapp.currencyList.data.remote.dto.ExchangeRateResponse
import co.king.lloydsapp.currencyList.domain.model.Currency
import co.king.lloydsapp.util.Resource
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response

class CurrencyRepositoryImplTest {

    lateinit var repositoryImpl: CurrencyRepositoryImpl
    lateinit var mockApi: CurrencyApi
    lateinit var mockDao: CurrencyDao


    @Before
    fun setUp() {
        mockApi = mock(CurrencyApi::class.java)
        mockDao = mock(CurrencyDao::class.java)
        repositoryImpl = CurrencyRepositoryImpl(mockApi, mockDao)
    }

    @Test
    fun `Given successful network call, fetchCurrency should return currencies from the network`() =
        runBlocking {
            `when`(mockApi.getCurrencyList()).thenReturn(
                Response.success(
                    mapOf(
                        Pair(
                            "AUD",
                            "Australian Dollar"
                        )
                    )
                )
            )
            `when`(mockApi.getExchangeRates()).thenReturn(
                Response.success(
                    ExchangeRateResponse(
                        1,
                        "Eur",
                        "12-10-2023",
                        mapOf(Pair("AUD", 2.3))
                    )
                )
            )

            val stream = repositoryImpl.fetchCurrencies().toList()


            verify(mockApi).getExchangeRates()
            verify(mockApi).getCurrencyList()
            verify(mockDao).observeCurrencyList()
            assertEquals(stream[0], Resource.Loading<List<Currency>>(isLoading = true))
            assertEquals(
                stream[1],
                Resource.Success(listOf(Currency("Australian Dollar", "AUD", 2.3)))
            )

        }


    @Test
    fun `Given failed network call, fetchCurrency should return currencies in the database`() =
        runBlocking {
            `when`(mockApi.getCurrencyList()).thenReturn(
                Response.error(
                    404,  ResponseBody.create(null, "")
                )
            )
            `when`(mockApi.getExchangeRates()).thenReturn(
                Response.error(
                    404, ResponseBody.create(null, "")
                )
            )
            `when`(mockDao.observeCurrencyList()).thenReturn(
                listOf(CurrencyEntity(1,"Australian Dollar", "AUD", 2.3))
            )

            val stream = repositoryImpl.fetchCurrencies().toList()


            verify(mockApi).getExchangeRates()
            verify(mockApi).getCurrencyList()
            verify(mockDao).observeCurrencyList()
            assertEquals(stream[0], Resource.Loading<List<Currency>>(isLoading = true))
            assertEquals(stream[1], Resource.Success(listOf(Currency("Australian Dollar", "AUD", 2.3))))

        }
}