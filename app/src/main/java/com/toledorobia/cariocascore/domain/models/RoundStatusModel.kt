package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.models.RoundStatus

data class RoundStatusModel(
    val gameRoundId: Int?,
    val gameId: Int?,
    val roundId: Int?,
    val roundName: String?,
    val roundCode: String?,
    val finished: Boolean,
)

fun RoundStatus.toDomain() = RoundStatusModel(gameRoundId, gameId, roundId, roundName, roundCode, finished)