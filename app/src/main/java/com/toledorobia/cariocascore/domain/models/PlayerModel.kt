package com.toledorobia.cariocascore.domain.models

import com.toledorobia.cariocascore.data.db.entities.PlayerEntity
import java.lang.Exception

data class PlayerModel(
    val id: Int?,
    val name: String,
    val deleted: Boolean
)

fun PlayerEntity.toDomain() = PlayerModel(id, name, deleted)