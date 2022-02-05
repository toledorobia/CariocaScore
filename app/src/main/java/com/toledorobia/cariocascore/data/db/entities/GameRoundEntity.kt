package com.toledorobia.cariocascore.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toledorobia.cariocascore.domain.models.GameRoundModel

@Entity(tableName = "game_round")
data class GameRoundEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "game_id")
    val gameId: Int?,

    @ColumnInfo(name = "round_id")
    val roundId: Int?,

    @ColumnInfo(name = "finished", defaultValue = "0")
    val finished: Boolean,
)

fun GameRoundModel.toEntity() = GameRoundEntity(id, gameId, roundId, finished)