package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.entities.GameRoundPlayerEntity

data class GameRoundPlayerModel(
    val id: Int?,
    val gameId: Int?,
    val roundId: Int?,
    val playerId: Int?,
    val score: Int?,
    val winner: Boolean,
)

fun GameRoundPlayerEntity.toDomain() = GameRoundPlayerModel(id, gameId, roundId, playerId, score, winner)