package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.GameDao
import com.toledorobia.cariocascore.data.db.entities.toEntity
import com.toledorobia.cariocascore.domain.models.GameStatusModel
import com.toledorobia.cariocascore.domain.models.GameModel
import com.toledorobia.cariocascore.domain.models.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gameDao: GameDao
){
    suspend fun insertOnDb(game: GameModel): Long {
        return gameDao.insert(game.toEntity())
    }

    suspend fun updateFinishedOnDb(gameId: Int?, finished: Boolean) {
        return gameDao.updateFinished(gameId, finished)
    }

    suspend fun updateDeletedOnDb(gameId: Int?, deleted: Boolean) {
        return gameDao.updateDeleted(gameId, deleted)
    }

    fun getByIdFromDb(id: Int?): Flow<GameModel> {
        return gameDao.getById(id).map {
            it.toDomain()
        }
    }

    fun getGamesForDashboardFromDb(): Flow<List<GameStatusModel>> {
        return gameDao.getStatus().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

}