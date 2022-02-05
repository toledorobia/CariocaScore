package com.toledorobia.cariocascore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toledorobia.cariocascore.data.db.entities.*
import com.toledorobia.cariocascore.data.db.models.GameDashboard
import kotlinx.coroutines.flow.Flow

@Dao
interface GameRoundPlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gameRoundPlayer: GameRoundPlayerEntity)
}