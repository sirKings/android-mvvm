package co.king.lloydsapp.currencyList.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.king.lloydsapp.currencyList.data.local.entities.CurrencyEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CurrencyEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    val symbol: String,
    val rate: Double
){
    companion object{
        const val TABLE_NAME = "currency"
    }
}
