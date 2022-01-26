package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.GameEntity

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    suspend fun getGames(): List<GameEntity>

    @Query("SELECT * FROM game WHERE id = :id")
    suspend fun getGame(id: Int): GameEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: GameEntity)
}