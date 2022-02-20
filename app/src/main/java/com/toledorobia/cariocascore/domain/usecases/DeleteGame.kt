package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.GamePlayerRepository
import com.toledorobia.cariocascore.data.db.repositories.GameRepository
import com.toledorobia.cariocascore.data.db.repositories.GameRoundPlayerRepository
import com.toledorobia.cariocascore.data.db.repositories.GameRoundRepository
import com.toledorobia.cariocascore.domain.models.*
import javax.inject.Inject

class DeleteGame @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke(gameId: Int?, deleted: Boolean) {
        gameRepository.updateDeletedOnDb(gameId, deleted)
    }
}