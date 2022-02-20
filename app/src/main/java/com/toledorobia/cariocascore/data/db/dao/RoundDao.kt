package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.GameRoundPlayerEntity
import com.toledorobia.cariocascore.data.db.entities.RoundEntity
import com.toledorobia.cariocascore.data.db.models.RoundStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface RoundDao {

    @Query("SELECT * FROM round")
    fun getAll(): Flow<List<RoundEntity>>

    @Query("SELECT * FROM round WHERE id = :id")
    fun getById(id: Int?): Flow<RoundEntity>

    @Query("SELECT game_round.id AS gameRoundId " +
            ", game_round.game_id AS gameId " +
            ", game_round.round_id AS roundId " +
            ", round.name AS roundName " +
            ", round.code AS roundCode " +
            ", game_round.finished " +
            "FROM round " +
            "JOIN game_round ON game_round.round_id = round.id " +
            "WHERE game_round.game_id = :gameId")
    fun getStatusByGame(gameId: Int?): Flow<List<RoundStatus>>

    @Query("SELECT * " +
            "FROM round " +
            "JOIN game_round_player ON game_round_player.round_id = round.id " +
            "WHERE game_round_player.game_id = :gameId " +
            "GROUP BY round.id")
    fun getRoundPlayerStatusByGame(gameId: Int?): Flow<Map<RoundEntity, List<GameRoundPlayerEntity>>>
}