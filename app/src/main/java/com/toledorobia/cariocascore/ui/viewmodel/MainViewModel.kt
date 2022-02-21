package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toledorobia.cariocascore.domain.usecases.GetGamesDashboard
import com.toledorobia.cariocascore.domain.usecases.GetPlayersWithStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getGamesDashboard: GetGamesDashboard,
    getPlayersWithStats: GetPlayersWithStats,
) : ViewModel() {
    val games = getGamesDashboard()
    val players = getPlayersWithStats()
    val withPlayers = MutableLiveData(false)

    init {
        viewModelScope.launch {
            players.collect {
                withPlayers.postValue(it.size > 1)
            }
        }
    }
}