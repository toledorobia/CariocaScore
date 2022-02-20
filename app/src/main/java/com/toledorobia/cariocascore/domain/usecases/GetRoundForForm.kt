package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.PlayerRepository
import com.toledorobia.cariocascore.data.db.repositories.RoundRepository
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.models.RoundModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoundForForm @Inject constructor(
    private val roundRepository: RoundRepository
) {
    operator fun invoke(id: Int?): Flow<RoundModel> {
        return roundRepository.getRoundFromDb(id)
    }
}