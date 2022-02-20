package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.PlayerEntity
import com.toledorobia.cariocascore.data.db.models.PlayerStats
import com.toledorobia.cariocascore.data.db.models.PlayerStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player WHERE deleted = 0")
    fun getAll(): Flow<List<PlayerEntity>>

    @Query("SELECT player.* " +
            "FROM player " +
            "JOIN game_player ON game_player.player_id = player.id " +
            "WHERE game_player.game_id = :gameId")
    fun getByGame(gameId: Int?): Flow<List<PlayerEntity>>

    @Query("SELECT player.id " +
            ", player.name AS playerName " +
            ", SUM(game_player.score) AS score " +
            ", game_player.winner " +
            ", game_player.loser " +
            "FROM player " +
            "JOIN game_player ON game_player.player_id = player.id " +
            "WHERE game_player.game_id = :gameId " +
            "GROUP BY player.id")
    fun getStatusByGame(gameId: Int?): Flow<List<PlayerStatus>>

    @Query("SELECT player.id " +
            ", player.name AS playerName " +
            ", IFNULL(SUM(CASE WHEN game.deleted = 0 THEN game_player.winner ELSE 0 END), 0) AS wins " +
            ", IFNULL(SUM(CASE WHEN game.deleted = 0 THEN game_player.loser ELSE 0 END), 0) AS losses " +
            ", IFNULL(CASE WHEN game.deleted = 0 THEN 1 ELSE 0 END, 0) AS matches " +
            "FROM player " +
            "LEFT JOIN game_player ON game_player.player_id = player.id " +
            "LEFT JOIN game ON game.id = game_player.game_id " +
            "WHERE player.deleted = 0 " +
            "GROUP BY player.id")
    fun getAllWithStats(): Flow<List<PlayerStats>>

    @Query("SELECT * FROM player WHERE id = :id")
    suspend fun getById(id: Int?): PlayerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(player: PlayerEntity)
}