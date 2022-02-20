package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.GameRoundPlayerRepository
import com.toledorobia.cariocascore.domain.models.GameScoreModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameScores @Inject constructor(
    private val gameRoundPlayerRepository: GameRoundPlayerRepository
) {
    operator fun invoke(gameId: Int?): Flow<List<GameScoreModel>> {
        return gameRoundPlayerRepository.getGameScoresFromDb(gameId)
    }
}