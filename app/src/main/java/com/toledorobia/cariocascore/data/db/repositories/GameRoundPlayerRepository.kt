package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.GameRoundPlayerDao
import com.toledorobia.cariocascore.data.db.entities.toEntity
import com.toledorobia.cariocascore.domain.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRoundPlayerRepository @Inject constructor(
    private val gameRoundPlayerDao: GameRoundPlayerDao
){
    suspend fun insertOnDb(gameRoundPlayer: GameRoundPlayerModel) {
        gameRoundPlayerDao.insert(gameRoundPlayer.toEntity())
    }

    suspend fun updateOneOnDb(id: Int?, score: Int?, winner: Boolean) {
        gameRoundPlayerDao.updateOne(id, score, winner)
    }

    suspend fun getByGameFromDb(gameId: Int?): List<GameRoundPlayerModel> {
        return gameRoundPlayerDao.getByGame(gameId).map {
            it.toDomain()
        }
    }

    fun getGameScoresFromDb(gameId: Int?): Flow<List<GameScoreModel>> {
        return gameRoundPlayerDao.getGameScores(gameId).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    fun getRoundsScoresFromDb(gameId: Int?): Flow<List<GameScoreModel>> {
        return gameRoundPlayerDao.getRoundsScores(gameId).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    fun getRoundsScoresForFormFromDb(gameId: Int?, roundId: Int?): List<GameScoreModel> {
        return gameRoundPlayerDao.getRoundsScoresForForm(gameId, roundId).map {
            it.toDomain()
        }
    }
}