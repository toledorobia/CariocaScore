package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.RoundRepository
import com.toledorobia.cariocascore.domain.models.RoundStatusModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoundsStatusByGame @Inject constructor(
    private val roundRepository: RoundRepository
) {
    operator fun invoke(gameId: Int?): Flow<List<RoundStatusModel>> {
        return roundRepository.getRoundByGameFromDb(gameId)
    }
}