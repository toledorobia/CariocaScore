package com.toledorobia.cariocascore.data.db.models

data class RoundStatus (
    val gameRoundId: Int?,
    val gameId: Int?,
    val roundId: Int?,
    val roundName: String?,
    val roundCode: String?,
    val finished: Boolean,
)
