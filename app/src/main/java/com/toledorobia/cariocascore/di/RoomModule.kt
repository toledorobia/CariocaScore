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
        Room.databaseBuilder(context, CariocaDatabase::class.java, DATABASE_NAME).build()


}