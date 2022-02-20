package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.*
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.core.Logger
import com.toledorobia.cariocascore.core.Utils
import com.toledorobia.cariocascore.domain.models.GameModel
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.models.RoundModel
import com.toledorobia.cariocascore.domain.usecases.CreateGame
import com.toledorobia.cariocascore.domain.usecases.GetPlayersForWizard
import com.toledorobia.cariocascore.domain.usecases.GetRoundsForWizard
import com.toledorobia.cariocascore.ui.event.FormEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GameWizardViewModel @Inject constructor(
    getRoundsForWizard: GetRoundsForWizard,
    getPlayersForWizard: GetPlayersForWizard,
    val createGame: CreateGame,
    logger: Logger,
    val utils: Utils,
) : ViewModel() {
    val enablePrev = MutableLiveData(false)
    val enableNext = MutableLiveData(true)
    val visibleSave = MutableLiveData(false)
    val visibleCancel = MutableLiveData(true)

    var gameName = MutableLiveData("")
    val players = getPlayersForWizard().stateIn(viewModelScope, SharingStarted.Lazily, null)
    val playersSelected = mutableListOf<Int>()

    val rounds = getRoundsForWizard().stateIn(viewModelScope, SharingStarted.Lazily, null)
    val roundsSelected = mutableListOf<Int>()

    private val _formEventChannel = Channel<FormEvent>()
    val formEvent = _formEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            rounds.collectLatest { list ->
                if (list != null) {
                    roundsSelected.addAll(0, list.map { it.id!! })
                    logger.d("Rounds selected: ${roundsSelected.size}")
                }
            }
        }
    }

    fun setGameName(name: String) {
        gameName.postValue(name)
    }

    fun setRound(round: RoundModel, checked: Boolean) {
        val index = roundsSelected.indexOf(round.id)

        if (checked && index == -1) {
            roundsSelected.add(round.id!!)
        }
        else if (!checked && index != -1) {
            roundsSelected.removeAt(index)
        }
    }

    fun setPlayer(player: PlayerModel, checked: Boolean) {
        val index = playersSelected.indexOf(player.id)

        if (checked && index == -1) {
            playersSelected.add(player.id!!)
        }
        else if (!checked && index != -1) {
            playersSelected.removeAt(index)
        }
    }

    fun onChangePage(position: Int, totalPages: Int) {
        if (position == 0) {
            enablePrev.postValue(false)
            visibleCancel.postValue(true)
        }
        else if (position == totalPages - 1) {
            enableNext.postValue(false)
            visibleSave.postValue(true)
        }
        else {
            enablePrev.postValue(true)
            enableNext.postValue(true)
            visibleSave.postValue(false)
            visibleCancel.postValue(false)
        }
    }

    // TODO set loading when saving
    fun saveGame() {
        viewModelScope.launch {
            when {
                gameName.value.isNullOrEmpty() -> {
                    _formEventChannel.send(FormEvent.Error(utils.str(R.string.val_cant_be_empty, utils.str(R.string.name))))
                }
                playersSelected.size < 2 -> {
                    _formEventChannel.send(FormEvent.Error(utils.str(R.string.val_required_players)))
                }
                roundsSelected.isEmpty() -> {
                    _formEventChannel.send(FormEvent.Error(utils.str(R.string.val_required_rounds)))
                }
                else -> {
                    withContext(Dispatchers.IO) {
                        val playersToSave = players.value?.filter {
                            playersSelected.contains(it.id)
                        }?.sortedBy {
                            it.id
                        }

                        val roundsToSave = rounds.value?.filter {
                            roundsSelected.contains(it.id)
                        }?.sortedBy {
                            it.id
                        }

                        val game = GameModel(
                            id = null,
                            name = gameName.value!!,
                            finished = false,
                            deleted = false,
                        )

                        createGame(game, roundsToSave!!, playersToSave!!)
                        _formEventChannel.send(FormEvent.Success(utils.str(R.string.msg_created, utils.str(R.string.game)), true))
                    }
                }
            }
        }
    }
}

