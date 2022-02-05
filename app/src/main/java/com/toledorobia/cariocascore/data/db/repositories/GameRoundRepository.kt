package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.GameRoundDao
import com.toledorobia.cariocascore.data.db.entities.toEntity
import com.toledorobia.cariocascore.domain.models.*
import javax.inject.Inject

class GameRoundRepository @Inject constructor(
    private val gameRoundDao: GameRoundDao
){
    suspend fun insertOnDb(gameRound: GameRoundModel): Long {
        return gameRoundDao.insert(gameRound.toEntity())
    }
}