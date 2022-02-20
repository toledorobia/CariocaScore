package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.models.PlayerStatus

data class PlayerStatusModel(
    val id: Int?,
    val playerName: String?,
    val score: Int?,
    val winner: Boolean,
    val loser: Boolean,
)

fun PlayerStatus.toDomain() = PlayerStatusModel(id, playerName, score, winner, loser)
