package com.toledorobia.cariocascore.data.db.models

data class GameStatus (
    val id: Int?,
    val name: String?,
    val finished: Boolean?,
    val players: Int?,
    val rounds: Int?,
    val roundsFinished: Int?,
    val winner: String?,
    val loser: String?,
)
