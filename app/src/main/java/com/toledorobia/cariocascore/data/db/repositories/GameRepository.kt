package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.GameDao
import com.toledorobia.cariocascore.data.db.entities.toEntity
import com.toledorobia.cariocascore.data.db.models.GameDashboard
import com.toledorobia.cariocascore.domain.models.GameDashboardModel
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

    fun getGamesForDashboard(): Flow<List<GameDashboardModel>> {
        return gameDao.getGamesForDashboard().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

}