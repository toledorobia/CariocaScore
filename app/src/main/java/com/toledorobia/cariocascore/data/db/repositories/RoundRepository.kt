package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.RoundDao
import com.toledorobia.cariocascore.domain.models.GameRoundPlayerModel
import com.toledorobia.cariocascore.domain.models.RoundModel
import com.toledorobia.cariocascore.domain.models.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoundRepository @Inject constructor(
    private val roundDao: RoundDao
){
    fun getRoundsFromDb(): Flow<List<RoundModel>> {
        return roundDao.getRounds().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    fun getRoundByGameFromDb(gameId: Int?): Flow<List<RoundModel>> {
        return roundDao.getRoundsByGame(gameId).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    fun getRoundsResultsByGameFromDb(gameId: Int?): Flow<Map<RoundModel, List<GameRoundPlayerModel>>> {
        return roundDao.getRoundsResultsByGame(gameId).map { map ->
            map.map { entry ->
                entry.key.toDomain() to entry.value.map {
                    it.toDomain()
                }
            }.toMap()
        }
    }
}