package co.king.lloydsapp.di

import android.app.Application
import androidx.room.Room
import co.king.lloydsapp.currencyList.data.local.CurrencyDatabase
import co.king.lloydsapp.currencyList.data.remote.CurrencyApi
import co.king.lloydsapp.currencyList.data.remote.dto.ExchangeRateResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Response
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object AppModuleTest {

    @Provides
    @Singleton
    fun provideApi(): CurrencyApi {
        val currencyApi = mock(CurrencyApi::class.java)
        runBlocking {
            Mockito.`when`(currencyApi.getCurrencyList()).thenReturn(
                Response.success(
                    mapOf(
                        Pair(
                            "AUD",
                            "Australian Dollar"
                        )
                    )
                )
            )
            Mockito.`when`(currencyApi.getExchangeRates()).thenReturn(
                Response.success(
                    ExchangeRateResponse(
                        1,
                        "Eur",
                        "12-10-2023",
                        mapOf(Pair("AUD", 2.3))
                    )
                )
            )
        }
        return currencyApi
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CurrencyDatabase = Room.inMemoryDatabaseBuilder(
        application,
        CurrencyDatabase::class.java
    ).build()

    @Provides
    @Singleton
    fun provideCurrencyDao(db: CurrencyDatabase) = db.currencyDao
}