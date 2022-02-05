package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.PlayerEntity
import com.toledorobia.cariocascore.data.db.entities.RoundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player WHERE deleted = 0")
    fun getPlayers(): Flow<List<PlayerEntity>>

    @Query("SELECT player.* " +
            "FROM player " +
            "JOIN game_player ON game_player.player_id = player.id " +
            "WHERE game_player.game_id = :gameId")
    fun getPlayersByGame(gameId: Int?): Flow<List<PlayerEntity>>

    @Query("SELECT * FROM player WHERE id = :id")
    suspend fun getPlayer(id: Int): PlayerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: PlayerEntity)
}