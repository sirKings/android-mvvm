package co.king.lloydsapp.di

import co.king.lloydsapp.currencyList.data.repository.CurrencyRepositoryImpl
import co.king.lloydsapp.currencyList.domain.repository.CurrencyRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    abstract fun bindRepository(
        repositoryImpl: CurrencyRepositoryImpl
    ): CurrencyRepository
}