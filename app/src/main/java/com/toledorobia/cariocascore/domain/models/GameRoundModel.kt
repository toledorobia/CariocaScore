package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.entities.GameRoundEntity

data class GameRoundModel(
    val id: Int?,
    val gameId: Int?,
    val roundId: Int?,
    val finished: Boolean,
)

fun GameRoundEntity.toDomain() = GameRoundModel(id, gameId, roundId, finished)