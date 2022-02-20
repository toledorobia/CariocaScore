package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.RoundDao
import com.toledorobia.cariocascore.domain.models.RoundStatusModel
import com.toledorobia.cariocascore.domain.models.RoundModel
import com.toledorobia.cariocascore.domain.models.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoundRepository @Inject constructor(
    private val roundDao: RoundDao
){
    fun getRoundsFromDb(): Flow<List<RoundModel>> {
        return roundDao.getAll().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    fun getRoundFromDb(id: Int?): Flow<RoundModel> {
        return roundDao.getById(id).map {
            it.toDomain()
        }
    }

    fun getRoundByGameFromDb(gameId: Int?): Flow<List<RoundStatusModel>> {
        return roundDao.getStatusByGame(gameId).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}