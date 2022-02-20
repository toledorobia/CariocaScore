package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.*
import com.toledorobia.cariocascore.core.IntentKey
import com.toledorobia.cariocascore.domain.models.GameScoreModel
import com.toledorobia.cariocascore.domain.usecases.*
import com.toledorobia.cariocascore.ui.event.FormEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RoundFormViewModel @Inject constructor(
    state: SavedStateHandle,
    getRoundForForm: GetRoundForForm,
    getScoresForForm: GetScoresForForm,
    val saveRoundResults: SaveRoundResults,
) : ViewModel() {
    val gameId = state.get<Int>(IntentKey.GAME_ID)
    val roundId = state.get<Int>(IntentKey.ROUND_ID)
    val round = getRoundForForm(roundId)
    val results = MutableLiveData<List<GameScoreModel>>()
    private val scores = mutableMapOf<Int?, Int?>()

    private val _formEventChannel = Channel<FormEvent>()
    val formEvent = _formEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val gameResults = getScoresForForm(gameId, roundId)
                results.postValue(gameResults)
            }
        }
    }

    fun setScore(id: Int?, score: Int?) {
        scores[id] = score
    }

    fun saveRound() {
        viewModelScope.launch {
            _formEventChannel.send(FormEvent.Submitting(true))
            val notWinners = scores.size - 1

            when {
                scores.filterValues { it == null }.isNotEmpty() -> {
                    _formEventChannel.apply {
                        send(FormEvent.Error("You must set all players scores"))
                        send(FormEvent.Submitting(false))
                    }
                }
                scores.filterValues { it == 0 }.size != 1 -> {
                    _formEventChannel.apply {
                        send(FormEvent.Error("You must set one player with 0 as score (winner)"))
                        send(FormEvent.Submitting(false))
                    }
                }
                scores.filterValues { it!! > 0 }.size != notWinners -> {
                    _formEventChannel.apply {
                        send(FormEvent.Error("You must set one winner (0 score) and losers (more than 0 score)"))
                        send(FormEvent.Submitting(false))
                    }
                }
                else -> {
                    withContext(Dispatchers.IO) {
                        saveRoundResults(gameId, roundId, scores)
                    }

                    _formEventChannel.send(FormEvent.Success("Round set successfully", true))
                }
            }
        }
    }
}