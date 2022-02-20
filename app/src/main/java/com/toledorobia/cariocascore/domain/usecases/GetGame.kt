package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.GameRepository
import com.toledorobia.cariocascore.domain.models.GameModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGame @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(id: Int?): Flow<GameModel> {
        return gameRepository.getByIdFromDb(id)
    }
}