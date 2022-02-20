package com.toledorobia.cariocascore.data.db.models

data class PlayerStatus (
    val id: Int?,
    val playerName: String?,
    val score: Int?,
    val winner: Boolean,
    val loser: Boolean,
)
