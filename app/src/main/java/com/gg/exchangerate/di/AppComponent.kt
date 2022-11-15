package com.gg.exchangerate.di

import android.app.Application
import android.content.Context
import com.gg.data.di.DataModule
import com.gg.domain.currency.useCase.*
import com.gg.domain.storage.useCase.GetBaseCurrencyCodeUseCase
import com.gg.domain.storage.useCase.GetSortTypeUseCase
import com.gg.domain.storage.useCase.SetBaseCurrencyCodeUseCase
import com.gg.domain.storage.useCase.SetSortTypeUseCase
import com.gg.exchangerate.MainApplication
import com.gg.exchangerate.ui.exchange.ExchangeRateViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        CredentialsModule::class
    ],
)
interface AppComponent {

    fun inject(application: Application)
    fun context(): Context
    fun provideGetBaseCurrencyCodeUseCase(): GetBaseCurrencyCodeUseCase
    fun provideGetSortTypeUseCase(): GetSortTypeUseCase
    fun provideSetBaseCurrencyCodeUseCase(): SetBaseCurrencyCodeUseCase
    fun provideSetSortTypeCodeUseCase(): SetSortTypeUseCase

    fun provideGetCurrenciesUseCase(): GetCurrenciesUseCase
    fun provideRemoveFavouriteUseCase(): RemoveFavouriteUseCase
    fun provideSetFavouriteUseCase(): SetFavouriteUseCase
    fun provideUpdateCurrencyValuesUseCase(): UpdateCurrencyValuesUseCase

    fun injectExchangeRateViewModel(model: ExchangeRateViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        @BindsInstance
        fun application(application: MainApplication): Builder
        fun build(): AppComponent
    }

}