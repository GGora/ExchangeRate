package com.gg.data.di

import android.app.Application
import android.content.Context
import com.gg.data.currencies.datasource.CurrenciesDataSource
import com.gg.data.currencies.repository.CurrenciesRepository
import com.gg.database.db.AppDatabase
import com.gg.database.db.dao.CurrenciesDao
import com.gg.database.db.storage.datastorage.DataStorageSource
import com.gg.database.db.storage.repository.DataStorageRepository
import com.gg.domain.auth.AppCredentials
import com.gg.domain.currency.repository.ICurrencyRepository
import com.gg.domain.currency.useCase.*
import com.gg.domain.storage.repository.IDataStorageRepository
import com.gg.domain.storage.useCase.GetBaseCurrencyCodeUseCase
import com.gg.domain.storage.useCase.GetSortTypeUseCase
import com.gg.domain.storage.useCase.SetBaseCurrencyCodeUseCase
import com.gg.domain.storage.useCase.SetSortTypeUseCase
import com.gg.network.api.CurrenciesApi
import com.gg.network.httpclients.ExchangeRateHttp
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDataStorageSource(context: Context) = DataStorageSource(context)

    @Provides
    @Singleton
    fun provideDataStorageRepository(dataStorageSource: DataStorageSource): IDataStorageRepository =
        DataStorageRepository(dataStorageSource)


    @Provides
    @Singleton
    fun provideGetBaseCurrencyCodeUseCase(repository: IDataStorageRepository) =
        GetBaseCurrencyCodeUseCase(repository)

    @Provides
    @Singleton
    fun provideGetCurrenciesUseCase(repository: ICurrencyRepository): GetCurrenciesUseCase =
        GetCurrenciesUseCase(repository)

    @Provides
    @Singleton
    fun provideGetSortTypeUseCase(repository: IDataStorageRepository) =
        GetSortTypeUseCase(repository)

    @Provides
    @Singleton
    fun provideSetBaseCurrencyCodeUseCase(
        dataRepository: IDataStorageRepository,
        currRepository: ICurrencyRepository
    ) = SetBaseCurrencyCodeUseCase(dataRepository, currRepository)

    @Provides
    @Singleton
    fun provideSetSortTypeCodeUseCase(repository: IDataStorageRepository) =
        SetSortTypeUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFavouriteUseCase(repository: ICurrencyRepository): RemoveFavouriteUseCase =
        RemoveFavouriteUseCase(repository)

    @Provides
    @Singleton
    fun provideSetFavouriteUseCase(repository: ICurrencyRepository): SetFavouriteUseCase =
        SetFavouriteUseCase(repository)

    @Provides
    @Singleton
    fun provideUpdateCurrencyValuesUseCase(repository: ICurrencyRepository):
            UpdateCurrencyValuesUseCase = UpdateCurrencyValuesUseCase(repository)

    @Provides
    @Singleton
    fun provideUpdateCurrenciesListUseCase(repository: ICurrencyRepository):
            UpdateCurrenciesListUseCase = UpdateCurrenciesListUseCase(repository)

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return AppDatabase.getAppDatabase(application)
    }

    @Provides
    @Singleton
    fun provideCurrenciesDao(db: AppDatabase): CurrenciesDao {
        return db.currenciesDao()
    }

    @Provides
    @Singleton
    fun provideCurrenciesRepository(currenciesDataSource: CurrenciesDataSource)
            : ICurrencyRepository = CurrenciesRepository(currenciesDataSource)

    @Provides
    @Singleton
    fun provideCurrenciesDataSource(
        currenciesDao: CurrenciesDao,
        currenciesApi: CurrenciesApi
    ): CurrenciesDataSource =
        CurrenciesDataSource(currenciesDao, currenciesApi)

    @Provides
    @Singleton
    fun provideRetrofit(credentials: AppCredentials) =
        ExchangeRateHttp.getRetrofit(credentials.token)

    @Provides
    @Singleton
    fun provideCurrencyApi(retrofit: Retrofit): CurrenciesApi =
        retrofit.create(CurrenciesApi::class.java)
}