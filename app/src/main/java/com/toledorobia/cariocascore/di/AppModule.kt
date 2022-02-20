package com.toledorobia.cariocascore.di

import android.content.Context
import com.toledorobia.cariocascore.core.Logger
import com.toledorobia.cariocascore.core.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLogger() = Logger()

    @Singleton
    @Provides
    fun provideUtils(@ApplicationContext appContext: Context) = Utils(appContext)
}