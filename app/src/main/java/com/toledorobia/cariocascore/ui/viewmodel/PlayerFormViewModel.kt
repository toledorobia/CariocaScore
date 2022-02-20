package com.toledorobia.cariocascore.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.core.IntentKey
import com.toledorobia.cariocascore.core.Utils
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.usecases.GetPlayerForForm
import com.toledorobia.cariocascore.domain.usecases.SavePlayer
import com.toledorobia.cariocascore.ui.event.FormEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerFormViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val getPlayerForForm: GetPlayerForForm,
    private val savePlayer: SavePlayer,
    private val utils: Utils,
) : ViewModel() {
    var playerId: Int? = null
    val player = MutableLiveData<PlayerModel?>()
    val name = MutableLiveData("")

    private val _formEventChannel = Channel<FormEvent>()
    val formEvent = _formEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            if (state.contains(IntentKey.PLAYER_ID)) {
                playerId = state.get<Int>(IntentKey.PLAYER_ID)
                val p = getPlayerForForm(playerId)
                player.postValue(p)
            }
        }
    }

    fun setName(name: String) {
        this.name.postValue(name);
    }

    fun onSavePlayer() {
        viewModelScope.launch {
            _formEventChannel.send(FormEvent.Submitting(true))

            val namePerson = name.value;
            if (namePerson.isNullOrEmpty()) {
                _formEventChannel.apply {
                    send(FormEvent.Error(utils.str(R.string.val_cant_be_empty, utils.str(R.string.player_name))))
                    send(FormEvent.Submitting(false))
                }
            }
            else {
                savePlayer(PlayerModel(playerId, namePerson, false))
                _formEventChannel.send(FormEvent.Success(utils.str(R.string.msg_saved, utils.getString(R.string.player)), true))
            }
        }
    }

    fun onDeletePlayer() {
        if (player.value == null) {
            return
        }

        viewModelScope.launch {
            val player = player.value!!
            savePlayer(PlayerModel(player.id, player.name, true))
            _formEventChannel.send(FormEvent.Delete(utils.str(R.string.player_deleted)))
        }
    }
}