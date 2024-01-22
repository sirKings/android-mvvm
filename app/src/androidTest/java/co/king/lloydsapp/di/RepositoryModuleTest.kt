package co.king.lloydsapp.di

import co.king.lloydsapp.currencyList.data.repository.CurrencyRepositoryImpl
import co.king.lloydsapp.currencyList.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class RepositoryModuleTest {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: CurrencyRepositoryImpl
    ): CurrencyRepository
}