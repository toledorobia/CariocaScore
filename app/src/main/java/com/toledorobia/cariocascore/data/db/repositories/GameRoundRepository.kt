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

    suspend fun updateFinishedOnDb(gameId: Int?, roundId: Int?, finished: Boolean) {
        return gameRoundDao.updateFinished(gameId, roundId, finished)
    }

    fun getNotFinishedByGameFromDb(gameId: Int?): List<GameRoundModel> {
        return gameRoundDao.getNotFinishedByGame(gameId).map {
            it.toDomain()
        }
    }
}