package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.GamePlayerRepository
import com.toledorobia.cariocascore.data.db.repositories.GameRepository
import com.toledorobia.cariocascore.data.db.repositories.GameRoundPlayerRepository
import com.toledorobia.cariocascore.data.db.repositories.GameRoundRepository
import com.toledorobia.cariocascore.domain.models.*
import javax.inject.Inject

class CreateGame @Inject constructor(
    private val gameRepository: GameRepository,
    private val gameRoundRepository: GameRoundRepository,
    private val gamePlayerRepository: GamePlayerRepository,
    private val gameRoundPlayerRepository: GameRoundPlayerRepository,
) {
    suspend operator fun invoke(game: GameModel, rounds: List<RoundModel>, players: List<PlayerModel>) {
        var gameId = gameRepository.insertOnDb(game).toInt()

        players.forEach {
            gamePlayerRepository.insertOnDb(GamePlayerModel(
                id = null,
                gameId = gameId,
                playerId = it.id,
                score = 0,
                winner = false,
                loser = false,
            ))
        }

        rounds.forEach { round ->
            gameRoundRepository.insertOnDb(GameRoundModel(
                id = null,
                gameId = gameId,
                roundId = round.id,
                finished = false,
            ))

            players.forEach { player ->
                gameRoundPlayerRepository.insertOnDb(GameRoundPlayerModel(
                    id = null,
                    gameId = gameId,
                    roundId = round.id,
                    playerId = player.id,
                    score = null,
                    winner = false,
                ))
            }
        }
    }
}