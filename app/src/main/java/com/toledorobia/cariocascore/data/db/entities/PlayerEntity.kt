package com.toledorobia.cariocascore.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toledorobia.cariocascore.domain.models.PlayerModel

@Entity(tableName = "player")
data class PlayerEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo
    val deleted: Boolean,
)

fun PlayerModel.toEntity() = PlayerEntity(id, name, deleted)