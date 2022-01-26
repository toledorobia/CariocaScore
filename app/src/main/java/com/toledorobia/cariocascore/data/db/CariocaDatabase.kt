package com.toledorobia.cariocascore.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toledorobia.cariocascore.data.db.entities.GameEntity
import com.toledorobia.cariocascore.data.db.entities.PlayerEntity
import com.toledorobia.cariocascore.data.db.entities.RoundEntity

@Database(
    entities = [
        GameEntity::class,
        RoundEntity::class,
        PlayerEntity::class,
    ],
    version = 1
)
abstract class CariocaDatabase: RoomDatabase() {
}