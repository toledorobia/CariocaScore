package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.GamePlayerDao
import com.toledorobia.cariocascore.data.db.entities.toEntity
import com.toledorobia.cariocascore.domain.models.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamePlayerRepository @Inject constructor(
    private val gamePlayerDao: GamePlayerDao
){
    suspend fun insertOnDb(gamePlayer: GamePlayerModel): Long {
        return gamePlayerDao.insert(gamePlayer.toEntity())
    }

    suspend fun updateWinnerOnDb(gameId: Int?, playerId: Int?) {
        gamePlayerDao.updateWinner(gameId, playerId)
    }

    suspend fun updateLoserOnDb(gameId: Int?, playerId: Int?) {
        gamePlayerDao.updateLoser(gameId, playerId)
    }
}