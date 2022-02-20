package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.models.GameStatus

data class GameStatusModel(
    val id: Int?,
    val name: String?,
    val finished: Boolean?,
    val players: Int?,
    val rounds: Int?,
    val roundsFinished: Int?,
    val winner: String?
)

fun GameStatus.toDomain() = GameStatusModel(id, name, finished, players, rounds, roundsFinished, winner)