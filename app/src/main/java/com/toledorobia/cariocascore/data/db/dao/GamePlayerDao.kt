package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.GamePlayerEntity

@Dao
interface GamePlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gamePlayer: GamePlayerEntity): Long

    @Query("UPDATE game_player " +
            "SET winner = CASE WHEN player_id = :playerId THEN 1 ELSE 0 END " +
            "WHERE game_id = :gameId")
    suspend fun updateWinner(gameId: Int?, playerId: Int?)

    @Query("UPDATE game_player " +
            "SET loser = CASE WHEN player_id = :playerId THEN 1 ELSE 0 END " +
            "WHERE game_id = :gameId")
    suspend fun updateLoser(gameId: Int?, playerId: Int?)
}