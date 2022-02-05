package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.models.GameDashboard

data class GameDashboardModel(
    val id: Int?,
    val name: String?,
    val finished: Boolean?,
    val players: Int?,
    val rounds: Int?,
)

fun GameDashboard.toDomain() = GameDashboardModel(id, name, finished, players, rounds)