package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.GameRoundPlayerDao
import com.toledorobia.cariocascore.data.db.entities.toEntity
import com.toledorobia.cariocascore.domain.models.*
import javax.inject.Inject

class GameRoundPlayerRepository @Inject constructor(
    private val gameRoundPlayerDao: GameRoundPlayerDao
){
    suspend fun insertOnDb(gameRoundPlayer: GameRoundPlayerModel) {
        gameRoundPlayerDao.insert(gameRoundPlayer.toEntity())
    }
}