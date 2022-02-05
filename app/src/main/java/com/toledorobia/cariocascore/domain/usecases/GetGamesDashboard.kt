package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.GameRepository
import com.toledorobia.cariocascore.data.db.repositories.PlayerRepository
import com.toledorobia.cariocascore.domain.models.GameDashboardModel
import com.toledorobia.cariocascore.domain.models.PlayerModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesDashboard @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(): Flow<List<GameDashboardModel>> {
        return gameRepository.getGamesForDashboard()
    }
}