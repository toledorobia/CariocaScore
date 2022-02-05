package com.toledorobia.cariocascore.di

import android.content.Context
import androidx.room.Room
import com.toledorobia.cariocascore.data.db.CariocaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "carioca"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CariocaDatabase::class.java, DATABASE_NAME)
            .createFromAsset("carioca.db")
            .build()

    @Singleton
    @Provides
    fun provideRoundDao(db: CariocaDatabase) = db.getRoundDao()

    @Singleton
    @Provides
    fun providePlayerDao(db: CariocaDatabase) = db.getPlayerDao()

    @Singleton
    @Provides
    fun provideGameDao(db: CariocaDatabase) = db.getGameDao()

    @Singleton
    @Provides
    fun provideGameRoundDao(db: CariocaDatabase) = db.getGameRoundDao()

    @Singleton
    @Provides
    fun provideGamePlayerDao(db: CariocaDatabase) = db.getGamePlayerDao()

    @Singleton
    @Provides
    fun provideGameRoundPlayerDao(db: CariocaDatabase) = db.getGameRoundPlayerDao()
}