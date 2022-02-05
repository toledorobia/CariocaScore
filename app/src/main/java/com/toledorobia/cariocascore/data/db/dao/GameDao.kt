package com.toledorobia.cariocascore.data.db.dao

import androidx.room.*
import com.toledorobia.cariocascore.data.db.entities.GameEntity
import com.toledorobia.cariocascore.data.db.models.GameDashboard
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    suspend fun getGames(): List<GameEntity>

    @Query("SELECT * FROM game WHERE id = :id")
    suspend fun getGame(id: Int): GameEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: GameEntity): Long

    @Query(
        "SELECT game.id AS id" +
                ", game.name AS name " +
                ", game.finished AS finished " +
                ", COUNT(DISTINCT(game_player.id)) AS players " +
                ", COUNT(DISTINCT(game_round.id)) AS rounds " +
                "FROM game " +
                "LEFT JOIN game_player ON game_player.game_id = game.id " +
                "LEFT JOIN game_round ON game_round.game_id = game.id " +
                "WHERE game.deleted = 0 " +
                "GROUP BY game.id"
    )
    fun getGamesForDashboard(): Flow<List<GameDashboard>>
}