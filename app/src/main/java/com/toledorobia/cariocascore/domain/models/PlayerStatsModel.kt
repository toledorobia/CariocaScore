package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.models.PlayerStats


data class PlayerStatsModel(
    val id: Int?,
    val playerName: String?,
    val wins: Int?,
    val losses: Int?,
    val matches: Int?,
)

fun PlayerStats.toDomain() = PlayerStatsModel(id, playerName, wins, losses, matches)
