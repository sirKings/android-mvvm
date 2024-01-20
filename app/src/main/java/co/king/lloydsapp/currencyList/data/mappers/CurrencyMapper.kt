package co.king.lloydsapp.currencyList.data.mappers

import co.king.lloydsapp.currencyList.data.local.entities.CurrencyEntity
import co.king.lloydsapp.currencyList.domain.model.Currency


fun CurrencyEntity.toCurrency(): Currency {
    return Currency(id, name, symbol, rate)
}

