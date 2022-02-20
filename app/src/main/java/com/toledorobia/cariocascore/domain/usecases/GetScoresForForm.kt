package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.GameRoundPlayerRepository
import com.toledorobia.cariocascore.domain.models.GameScoreModel
import javax.inject.Inject

class GetScoresForForm @Inject constructor(
    private val gameRoundPlayerRepository: GameRoundPlayerRepository
) {
    operator fun invoke(gameId: Int?, roundId: Int?): List<GameScoreModel> {
        return gameRoundPlayerRepository.getRoundsScoresForFormFromDb(gameId, roundId)
    }
}