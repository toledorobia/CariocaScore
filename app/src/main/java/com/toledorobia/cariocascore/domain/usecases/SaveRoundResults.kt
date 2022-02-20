package com.toledorobia.cariocascore.domain.usecases

import com.toledorobia.cariocascore.data.db.repositories.*
import javax.inject.Inject

class SaveRoundResults @Inject constructor(
    private val gameRoundPlayerRepository: GameRoundPlayerRepository,
    private val gameRoundRepository: GameRoundRepository,
    private val gamePlayerRepository: GamePlayerRepository,
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke(gameId: Int?, roundId: Int?, results: Map<Int?, Int?>) {
        results.forEach {
            gameRoundPlayerRepository.updateOneOnDb(it.key, it.value, it.value == 0)
        }

        gameRoundRepository.updateFinishedOnDb(gameId, roundId, true)

        val notFinishedRounds = gameRoundRepository.getNotFinishedByGameFromDb(gameId)
        if (notFinishedRounds.isEmpty()) {
            gameRepository.updateFinishedOnDb(gameId, true)

            val results = gameRoundPlayerRepository.getByGameFromDb(gameId)

            val scores = results.groupBy {
                it.playerId
            }.mapValues { entry ->
                entry.value.sumOf {
                    it.score ?: 0
                }
            }

            val loserGame = scores.maxByOrNull {
                it.value
            }

            if (loserGame != null) {
                gamePlayerRepository.updateLoserOnDb(gameId, loserGame.key)
            }

            val winnerGame = scores.minByOrNull {
                it.value
            }

            if (winnerGame != null) {
                gamePlayerRepository.updateWinnerOnDb(gameId, winnerGame.key)
            }
        }
    }
}