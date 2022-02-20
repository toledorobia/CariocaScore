package com.toledorobia.cariocascore.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toledorobia.cariocascore.domain.models.GameRoundPlayerModel

@Entity(tableName = "game_round_player")
data class GameRoundPlayerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "game_id")
    val gameId: Int?,

    @ColumnInfo(name = "round_id")
    val roundId: Int?,

    @ColumnInfo(name = "player_id")
    val playerId: Int?,

    @ColumnInfo(name = "score", defaultValue = "0")
    val score: Int?,

    @ColumnInfo(name = "winner", defaultValue = "0")
    val winner: Boolean,
)

fun GameRoundPlayerModel.toEntity() = GameRoundPlayerEntity(id, gameId, roundId, playerId, score, winner)