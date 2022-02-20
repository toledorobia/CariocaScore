package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.*
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.usecases.GetPlayers
import com.toledorobia.cariocascore.domain.usecases.GetPlayersWithStats
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    getPlayersWithStats: GetPlayersWithStats,
) : ViewModel() {
    val players = getPlayersWithStats()
}