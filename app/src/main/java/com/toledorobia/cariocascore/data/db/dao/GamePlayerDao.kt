package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.GameEntity
import com.toledorobia.cariocascore.data.db.entities.GamePlayerEntity
import com.toledorobia.cariocascore.data.db.entities.GameRoundEntity
import com.toledorobia.cariocascore.data.db.entities.RoundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamePlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gamePlayer: GamePlayerEntity): Long
}