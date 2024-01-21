package co.king.lloydsapp.di

import co.king.lloydsapp.currencyList.data.repository.CurrencyRepositoryImpl
import co.king.lloydsapp.currencyList.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: CurrencyRepositoryImpl
    ): CurrencyRepository
}