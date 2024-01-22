package co.king.lloydsapp.currencyList.presentation

import co.king.lloydsapp.currencyList.domain.model.Currency
import co.king.lloydsapp.currencyList.domain.repository.CurrencyRepository
import co.king.lloydsapp.util.Resource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class CurrencyViewModelTest {

    lateinit var viewModel: CurrencyViewModel
    lateinit var mockRepository: CurrencyRepository

    @Before
    fun setUp() {
        mockRepository = mock(CurrencyRepository::class.java)
    }

    @Test
    fun `Given successful fetch of currencies, currency list state should be populated`() =
        runBlocking {
            val fakeCurrency = listOf(Currency("Australian Dollars", "AUD", 1.2))
            `when`(mockRepository.fetchCurrencies()).thenReturn(flowOf(Resource.Success(fakeCurrency)))

            viewModel = CurrencyViewModel(mockRepository)

            verify(mockRepository).fetchCurrencies()
            assertEquals(fakeCurrency, viewModel.state.value.items)
        }

    @Test
    fun `Given failed fetch of currencies, error message state should conatin error`() =
        runBlocking {
            val errorMessage = "Error occurred"
            `when`(mockRepository.fetchCurrencies()).thenReturn(flowOf(Resource.Failure(data = null,errorMessage = errorMessage)))

            viewModel = CurrencyViewModel(mockRepository)

            verify(mockRepository).fetchCurrencies()
            assertEquals(errorMessage, viewModel.state.value.errorMessage)
        }
}