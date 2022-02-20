package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.*
import com.toledorobia.cariocascore.data.db.models.GameScore
import kotlinx.coroutines.flow.Flow

@Dao
interface GameRoundPlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gameRoundPlayer: GameRoundPlayerEntity)

    @Query("UPDATE game_round_player SET score = :score, winner = :winner WHERE id = :id")
    suspend fun updateOne(id: Int?, score: Int?, winner: Boolean)

    @Query("SELECT * FROM game_round_player WHERE game_id = :gameId")
    suspend fun getByGame(gameId: Int?): List<GameRoundPlayerEntity>

    @Query("SELECT grp.id " +
            ", grp.game_id AS gameId " +
            ", null AS roundId " +
            ", player.id AS playerId " +
            ", player.name AS playerName " +
            ", SUM(grp.score) AS score " +
            ", game_player.winner " +
            ", game_player.loser " +
            "FROM game_round_player AS grp " +
            "JOIN game_player ON game_player.game_id = grp.game_id AND game_player.player_id = grp.player_id " +
            "JOIN player ON game_player.player_id = player.id " +
            "WHERE grp.game_id = :gameId " +
            "GROUP BY grp.player_id " +
            "ORDER BY score")
    fun getGameScores(gameId: Int?): Flow<List<GameScore>>

    @Query("SELECT grp.id " +
                ", grp.game_id AS gameId " +
                ", grp.round_id AS roundId " +
                ", grp.player_id AS playerId " +
                ", player.name AS playerName " +
                ", grp.score " +
                ", grp.winner " +
                "FROM game_round_player AS grp " +
                "JOIN player ON player.id = grp.player_id " +
                "WHERE grp.game_id = :gameId " +
                "ORDER BY grp.round_id, grp.player_id")
    fun getRoundsScores(gameId: Int?): Flow<List<GameScore>>

    @Query("SELECT grp.id " +
                ", grp.game_id AS gameId " +
                ", grp.round_id AS roundId " +
                ", grp.player_id AS playerId " +
                ", player.name AS playerName " +
                ", grp.score " +
                ", grp.winner " +
                "FROM game_round_player AS grp " +
                "JOIN player ON player.id = grp.player_id " +
                "WHERE grp.game_id = :gameId AND grp.round_id = :roundId " +
                "ORDER BY grp.player_id")
    fun getRoundsScoresForForm(gameId: Int?, roundId: Int?): List<GameScore>
}