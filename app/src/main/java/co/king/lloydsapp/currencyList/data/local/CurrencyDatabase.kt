package co.king.lloydsapp.currencyList.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.king.lloydsapp.currencyList.data.local.entities.CurrencyEntity

@Database(
    entities = [CurrencyEntity::class],
    version = 1
)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}