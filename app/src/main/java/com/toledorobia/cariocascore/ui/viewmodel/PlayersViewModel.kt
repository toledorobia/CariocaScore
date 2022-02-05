package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.*
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.usecases.GetPlayers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    getPlayers: GetPlayers,
) : ViewModel() {
    val players: LiveData<List<PlayerModel>> = getPlayers().asLiveData()
}