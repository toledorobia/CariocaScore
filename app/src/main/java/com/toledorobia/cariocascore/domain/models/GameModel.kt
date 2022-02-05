package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.entities.GameEntity

data class GameModel(
    val id: Int?,
    val name: String,
    val finished: Boolean,
    val deleted: Boolean,
)

fun GameEntity.toDomain() = GameModel(id, name, finished, deleted)