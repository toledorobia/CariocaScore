package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.PlayerRepository
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.models.PlayerStatsModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayersWithStats @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    operator fun invoke(): Flow<List<PlayerStatsModel>> {
        return playerRepository.getAllWithStatsFromDb()
    }
}