package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.PlayerRepository
import com.toledorobia.cariocascore.domain.models.InvalidPlayerException
import com.toledorobia.cariocascore.domain.models.PlayerModel
import javax.inject.Inject

class SavePlayer @Inject constructor(
    private val playerRepository: PlayerRepository
) {

    @Throws(InvalidPlayerException::class)
    suspend operator fun invoke(player: PlayerModel) {
        playerRepository.insertPlayerToDb(player)
    }
}