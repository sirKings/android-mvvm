package co.king.lloydsapp.currencyList.data.mappers

import co.king.lloydsapp.currencyList.data.local.entities.CurrencyEntity
import co.king.lloydsapp.currencyList.domain.model.Currency


fun CurrencyEntity.toCurrency(): Currency {
    return Currency(name, symbol, rate)
}

fun Currency.toEntity(): CurrencyEntity{
    return CurrencyEntity(name= name, symbol=symbol, rate = rate)
}

