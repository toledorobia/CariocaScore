package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.PlayerRepository
import com.toledorobia.cariocascore.domain.models.PlayerModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayers @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    operator fun invoke(): Flow<List<PlayerModel>> {
        return playerRepository.getFromDb()
    }
}