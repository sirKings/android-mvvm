package co.king.lloydsapp.di

import android.app.Application
import androidx.room.Room
import co.king.lloydsapp.currencyList.data.local.CurrencyDatabase
import co.king.lloydsapp.currencyList.data.remote.CurrencyApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val BASE_URL = "https://api.frankfurter.app"

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    fun provideApi(): CurrencyApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()
        .create()

    fun provideDatabase(app: Application): CurrencyDatabase =
        Room.databaseBuilder(
            app, CurrencyDatabase::class.java,
            "currency.db"
        ).build()

}