package com.toledorobia.cariocascore.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toledorobia.cariocascore.domain.models.GamePlayerModel

@Entity(tableName = "game_player")
data class GamePlayerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "game_id")
    val gameId: Int?,

    @ColumnInfo(name = "player_id")
    val playerId: Int?,

    @ColumnInfo(name = "score", defaultValue = "0")
    val score: Int,

    @ColumnInfo(name = "winner")
    val winner: Boolean,

    @ColumnInfo(name = "loser")
    val loser: Boolean,
)

fun GamePlayerModel.toEntity() = GamePlayerEntity(id, gameId, playerId, score, winner, loser)
