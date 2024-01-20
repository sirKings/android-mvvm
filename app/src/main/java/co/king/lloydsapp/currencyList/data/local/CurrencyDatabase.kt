package co.king.lloydsapp.currencyList.data.local

import androidx.room.RoomDatabase

abstract class CurrencyDatabase: RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}