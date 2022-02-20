package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.*
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.core.IntentKey
import com.toledorobia.cariocascore.core.Utils
import com.toledorobia.cariocascore.domain.usecases.*
import com.toledorobia.cariocascore.ui.event.FormEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamePanelViewModel @Inject constructor(
    state: SavedStateHandle,
    val utils: Utils,
    getGame: GetGame,
    getRoundsStatusByGame: GetRoundsStatusByGame,
    getGameScores: GetGameScores,
    getRoundsScores: GetRoundsScores,
    val deleteGame: DeleteGame,
) : ViewModel() {
    val gameId = state.get<Int>(IntentKey.GAME_ID)
    val game = getGame(gameId)
    val rounds = getRoundsStatusByGame(gameId)
    val roundsScores = getRoundsScores(gameId)
    val gameScores = getGameScores(gameId)

    private val _formEventChannel = Channel<FormEvent>()
    val formEvent = _formEventChannel.receiveAsFlow()

    fun onDeleteGame() {
        viewModelScope.launch {
            deleteGame(gameId, true)
            _formEventChannel.send(FormEvent.Delete(utils.getString(R.string.game_deleted)))
        }
    }
}