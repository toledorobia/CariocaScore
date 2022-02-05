package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.GameRoundPlayerEntity
import com.toledorobia.cariocascore.data.db.entities.PlayerEntity
import com.toledorobia.cariocascore.data.db.entities.RoundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoundDao {

    @Query("SELECT * FROM round")
    fun getRounds(): Flow<List<RoundEntity>>

    @Query("SELECT round.* " +
            "FROM round " +
            "JOIN game_round ON game_round.round_id = round.id " +
            "WHERE game_round.game_id = :gameId")
    fun getRoundsByGame(gameId: Int?): Flow<List<RoundEntity>>

    @Query("SELECT * " +
            "FROM round " +
            "JOIN game_round_player ON game_round_player.round_id = round.id " +
            "WHERE game_round_player.game_id = :gameId " +
            "GROUP BY round.id")
    fun getRoundsResultsByGame(gameId: Int?): Flow<Map<RoundEntity, List<GameRoundPlayerEntity>>>
}