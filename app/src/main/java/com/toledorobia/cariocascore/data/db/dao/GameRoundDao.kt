package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.GameRoundEntity

@Dao
interface GameRoundDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gameRound: GameRoundEntity): Long

    @Query("UPDATE game_round " +
            "SET finished = :finished " +
            "WHERE game_id = :gameId AND round_id = :roundId")
    suspend fun updateFinished(gameId: Int?, roundId: Int?, finished: Boolean)

    @Query("SELECT * FROM game_round WHERE game_id = :gameId AND finished = 0")
    fun getNotFinishedByGame(gameId: Int?): List<GameRoundEntity>

}