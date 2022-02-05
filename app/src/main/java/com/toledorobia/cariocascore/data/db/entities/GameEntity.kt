package com.toledorobia.cariocascore.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toledorobia.cariocascore.domain.models.GameModel

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "finished")
    val finished: Boolean,

    @ColumnInfo(name = "deleted")
    val deleted: Boolean,
)

fun GameModel.toEntity() = GameEntity(id, name, finished, deleted)
