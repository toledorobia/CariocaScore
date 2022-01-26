package com.toledorobia.cariocascore.domain.model

import com.toledorobia.cariocascore.data.db.entities.GameEntity

data class Game(
    val id: Int,
    val name: String,
)

fun GameEntity.toDomain() = Game(id, name)
