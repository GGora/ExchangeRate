package com.gg.exchangerate.di

import com.gg.domain.auth.AppCredentials
import com.gg.exchangerate.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CredentialsModule {

    @Provides
    @Singleton
    fun provideAppCredentials(): AppCredentials = AppCredentials(BuildConfig.EXCH_TOKEN)
}