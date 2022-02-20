package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.models.GameScore

data class GameScoreModel(
    val id: Int?,
    val gameId: Int?,
    val roundId: Int?,
    val playerId: Int?,
    val playerName: String?,
    val score: Int?,
    val winner: Boolean,
)

fun GameScore.toDomain() = GameScoreModel(id, gameId, roundId, playerId, playerName, score, winner)