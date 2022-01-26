package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameWizardViewModel @Inject constructor() : ViewModel() {
    val enablePrev = MutableLiveData(false)
    val enableNext = MutableLiveData(true)
    val visibleSave = MutableLiveData(false)

    val players = MutableLiveData<MutableList<String>>().apply {
        postValue(mutableListOf<String>())
    }

    fun addPlayer() {
        val list = players.value
        list?.add("")
        players.postValue(list)
    }

    fun removePlayer(index: Int) {
        val list = players.value
        list?.removeAt(index)
        players.postValue(list)
    }

    fun changePlayerName(index: Int, name: String) {
        val list = players.value!!
        list[index] = name
        players.postValue(list)
    }

    fun onFirstPage() {
        enablePrev.postValue(false)
    }

    fun onMiddlePage() {
        enablePrev.postValue(true)
        enableNext.postValue(true)
        visibleSave.postValue(false)
    }

    fun onLastPage () {
        enableNext.postValue(false)
        visibleSave.postValue(true)
    }
}