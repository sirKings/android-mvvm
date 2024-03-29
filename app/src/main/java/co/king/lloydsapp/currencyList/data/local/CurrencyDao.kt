package co.king.lloydsapp.currencyList.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import co.king.lloydsapp.currencyList.data.local.entities.CurrencyEntity

@Dao
interface CurrencyDao {

    @Upsert
    fun saveCurrencyList(items: List<CurrencyEntity>)

    @Query("select * from currency")
    fun observeCurrencyList(): List<CurrencyEntity>
}