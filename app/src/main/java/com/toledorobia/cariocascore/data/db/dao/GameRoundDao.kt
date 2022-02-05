package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.GameEntity
import com.toledorobia.cariocascore.data.db.entities.GameRoundEntity
import com.toledorobia.cariocascore.data.db.entities.RoundEntity

@Dao
interface GameRoundDao {

    @Query("SELECT * FROM game_round")
    suspend fun getGamesRounds(): List<GameRoundEntity>

    @Query("SELECT * FROM game_round WHERE game_id = :gameId")
    suspend fun getGamesRoundsByGame(gameId: Int): List<GameRoundEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gameRound: GameRoundEntity): Long
}