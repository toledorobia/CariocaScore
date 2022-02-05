package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.GameRoundRepository
import com.toledorobia.cariocascore.data.db.repositories.PlayerRepository
import com.toledorobia.cariocascore.data.db.repositories.RoundRepository
import com.toledorobia.cariocascore.domain.models.GameRoundPlayerModel
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.models.RoundModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoundsResultsByGame @Inject constructor(
    private val roundRepository: RoundRepository
) {
    operator fun invoke(gameId: Int?): Flow<Map<RoundModel, List<GameRoundPlayerModel>>> {
        return roundRepository.getRoundsResultsByGameFromDb(gameId)
    }
}