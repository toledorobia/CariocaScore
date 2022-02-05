package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.entities.RoundEntity

data class RoundModel(
    val id: Int,
    val name: String,
    val code: String
)

fun RoundEntity.toDomain() = RoundModel(id, name, code)
