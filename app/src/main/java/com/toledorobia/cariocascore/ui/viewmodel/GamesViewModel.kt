package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.toledorobia.cariocascore.domain.usecases.GetGamesDashboard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    getGamesDashboard: GetGamesDashboard,
) : ViewModel() {
    val games = getGamesDashboard()
}