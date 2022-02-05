package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.RoundRepository
import com.toledorobia.cariocascore.domain.models.RoundModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoundsForWizard @Inject constructor(
    private val roundRepository: RoundRepository
) {

    operator fun invoke(): Flow<List<RoundModel>> {
        return roundRepository.getRoundsFromDb()
    }
}