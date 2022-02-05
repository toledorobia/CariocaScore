package com.toledorobia.cariocascore.data.db.repositories

import com.toledorobia.cariocascore.data.db.dao.PlayerDao
import com.toledorobia.cariocascore.data.db.entities.toEntity
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.models.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val playerDao: PlayerDao
){
    fun getPlayersFromDb(): Flow<List<PlayerModel>> {
        return playerDao.getPlayers().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    fun getPlayersByGameFromDb(gameId: Int?): Flow<List<PlayerModel>> {
        return playerDao.getPlayersByGame(gameId).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    suspend fun insertPlayerToDb(player: PlayerModel) {
        playerDao.insertPlayer(player.toEntity())
    }
}