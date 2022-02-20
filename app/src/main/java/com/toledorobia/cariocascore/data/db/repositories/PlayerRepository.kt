package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.PlayerDao
import com.toledorobia.cariocascore.data.db.entities.toEntity
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.models.PlayerStatsModel
import com.toledorobia.cariocascore.domain.models.PlayerStatusModel
import com.toledorobia.cariocascore.domain.models.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val playerDao: PlayerDao
){
    suspend fun getByIdFromDb(id: Int?): PlayerModel? {
        return playerDao.getById(id)?.toDomain()
    }

    fun getFromDb(): Flow<List<PlayerModel>> {
        return playerDao.getAll().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    fun getByGameFromDb(gameId: Int?): Flow<List<PlayerModel>> {
        return playerDao.getByGame(gameId).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    fun getStatusByGameFromDb(gameId: Int?): Flow<List<PlayerStatusModel>> {
        return playerDao.getStatusByGame(gameId).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    fun getAllWithStatsFromDb(): Flow<List<PlayerStatsModel>> {
        return playerDao.getAllWithStats().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    suspend fun insertOnDb(player: PlayerModel) {
        playerDao.insert(player.toEntity())
    }
}