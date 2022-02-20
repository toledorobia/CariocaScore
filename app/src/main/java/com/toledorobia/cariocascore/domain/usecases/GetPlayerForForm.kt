package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.PlayerRepository
import com.toledorobia.cariocascore.domain.models.PlayerModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayerForForm @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    suspend operator fun invoke(id: Int?): PlayerModel? {
        return playerRepository.getByIdFromDb(id)
    }
}