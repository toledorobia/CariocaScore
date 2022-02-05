package com.toledorobia.cariocascore.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toledorobia.cariocascore.data.db.dao.*
import com.toledorobia.cariocascore.data.db.entities.*

@Database(
    entities = [
        GameEntity::class,
        RoundEntity::class,
        PlayerEntity::class,
        GameRoundEntity::class,
        GamePlayerEntity::class,
        GameRoundPlayerEntity::class,
    ],
    version = 1
)
abstract class CariocaDatabase: RoomDatabase() {
    abstract fun getRoundDao(): RoundDao
    abstract fun getPlayerDao(): PlayerDao
    abstract fun getGameDao(): GameDao
    abstract fun getGameRoundDao(): GameRoundDao
    abstract fun getGamePlayerDao(): GamePlayerDao
    abstract fun getGameRoundPlayerDao(): GameRoundPlayerDao
}