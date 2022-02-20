package com.toledorobia.cariocascore.data.db.models

data class GameScore (
    val id: Int?,
    val gameId: Int?,
    val roundId: Int?,
    val playerId: Int?,
    val playerName: String?,
    val score: Int?,
    val winner: Boolean,
)
