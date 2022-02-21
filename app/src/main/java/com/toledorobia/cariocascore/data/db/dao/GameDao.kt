package com.toledorobia.cariocascore.data.db.dao

import androidx.room.*
import com.toledorobia.cariocascore.data.db.entities.GameEntity
import com.toledorobia.cariocascore.data.db.models.GameStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game WHERE id = :id")
    fun getById(id: Int?): Flow<GameEntity>

    @Query(
        "SELECT game.id AS id" +
                ", game.name AS name " +
                ", game.finished AS finished " +
                ", COUNT(DISTINCT(game_player.id)) AS players " +
                ", COUNT(DISTINCT(game_round.id)) AS rounds " +
                ", 0 AS roundsFinished " +
                ", CASE WHEN game_player.winner = 1 THEN player.name ELSE null END AS winner " +
                ", CASE WHEN game_player.loser = 1 THEN player.name ELSE null END AS loser " +
                "FROM game " +
                "LEFT JOIN game_round ON game_round.game_id = game.id " +
                "LEFT JOIN game_player ON game_player.game_id = game.id " +
                "LEFT JOIN player ON game_player.player_id = player.id " +
                "WHERE game.deleted = 0 " +
                "GROUP BY game.id " +
                "ORDER BY game.id DESC"
    )
    fun getStatus(): Flow<List<GameStatus>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: GameEntity): Long

    @Query("UPDATE game " +
            "SET finished = :finished " +
            "WHERE id = :gameId")
    suspend fun updateFinished(gameId: Int?, finished: Boolean)

    @Query("UPDATE game " +
            "SET deleted = :deleted " +
            "WHERE id = :gameId")
    suspend fun updateDeleted(gameId: Int?, deleted: Boolean?)
}