package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.GameRepository
import com.toledorobia.cariocascore.domain.models.GameStatusModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesDashboard @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(): Flow<List<GameStatusModel>> {
        return gameRepository.getGamesForDashboardFromDb()
    }
}