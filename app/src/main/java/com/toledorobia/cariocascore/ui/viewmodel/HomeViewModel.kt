package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.toledorobia.cariocascore.domain.models.GameDashboardModel
import com.toledorobia.cariocascore.domain.usecases.GetGamesDashboard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getGamesDashboard: GetGamesDashboard,
) : ViewModel() {
    val games: LiveData<List<GameDashboardModel>> = getGamesDashboard().asLiveData()
}