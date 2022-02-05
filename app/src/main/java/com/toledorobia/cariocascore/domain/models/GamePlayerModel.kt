package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.entities.GamePlayerEntity

data class GamePlayerModel(
    val id: Int?,
    val gameId: Int?,
    val playerId: Int?,
    val score: Int,
    val winner: Boolean,
)

fun GamePlayerEntity.toDomain() = GamePlayerModel(id, gameId, playerId, score, winner)