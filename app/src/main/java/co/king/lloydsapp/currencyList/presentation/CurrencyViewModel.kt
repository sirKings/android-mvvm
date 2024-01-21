package co.king.lloydsapp.currencyList.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.king.lloydsapp.currencyList.domain.repository.CurrencyRepository
import co.king.lloydsapp.util.Resource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor
    (private val repository: CurrencyRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(CurrencyListState())
    val state = _state.asStateFlow()

    init {
        fetchCurrencies()
    }

    private fun fetchCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchCurrencies().collect {
                when (it) {
                    is Resource.Loading -> {

                    }

                    is Resource.Failure -> {

                    }

                    is Resource.Success -> {
                        _state.update { currencyListState ->
                            currencyListState.copy(items = it.data)
                        }
                    }
                }
            }
        }
    }
}