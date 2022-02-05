package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toledorobia.cariocascore.domain.models.InvalidPlayerException
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.usecases.SavePlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerFormViewModel @Inject constructor(
    private val savePlayer: SavePlayer
) : ViewModel() {

    val loading = MutableLiveData<Boolean>(false)
    val name = MutableLiveData<String>("")
    val playerId: Int? = null

    fun onCreate() {
//        viewModelScope.launch {
//            val roundsList = withContext(Dispatchers.IO) {
//                getRoundsForWizard()
//            }
//            roundsSelected.addAll(0, roundsList.map {
//                it.id
//            })
//            rounds.postValue(roundsList)
//        }
    }

    fun setName(name: String) {
        this.name.postValue(name);
    }

    fun setLoading(value: Boolean) {
        loading.postValue(value)
    }

    @Throws(InvalidPlayerException::class)
    fun onSavePlayer() {
        setLoading(true)

        val namePerson = name.value;
        if (namePerson == null || namePerson!!.isEmpty()) {
            setLoading(false)
            throw InvalidPlayerException("The name can't be empty")
        }

        viewModelScope.launch {
            savePlayer(PlayerModel(playerId, namePerson, false))
            setLoading(false)
        }
    }
}