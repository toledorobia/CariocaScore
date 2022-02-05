package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.*
import com.toledorobia.cariocascore.core.IntentKey
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.models.RoundModel
import com.toledorobia.cariocascore.domain.usecases.GetPlayers
import com.toledorobia.cariocascore.domain.usecases.GetPlayersByGame
import com.toledorobia.cariocascore.domain.usecases.GetRoundsByGame
import com.toledorobia.cariocascore.domain.usecases.GetRoundsResultsByGame
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamePanelViewModel @Inject constructor(
    state: SavedStateHandle,
    getPlayersByGame: GetPlayersByGame,
    getRoundsByGame: GetRoundsByGame,
    getRoundsResultsByGame: GetRoundsResultsByGame,
) : ViewModel() {
    val players = getPlayersByGame(state.get<Int>(IntentKey.GAME_ID)).asLiveData()
    val rounds = getRoundsResultsByGame(state.get<Int>(IntentKey.GAME_ID)).asLiveData()

}