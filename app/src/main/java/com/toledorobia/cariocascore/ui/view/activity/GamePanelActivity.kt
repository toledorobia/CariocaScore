package com.toledorobia.cariocascore.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.core.IntentKey
import com.toledorobia.cariocascore.databinding.ActivityGamePanelBinding
import com.toledorobia.cariocascore.ui.adapter.GamePanelAdapter
import com.toledorobia.cariocascore.ui.event.FormEvent
import com.toledorobia.cariocascore.ui.viewmodel.GamePanelViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GamePanelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGamePanelBinding
    private val gamePanelViewModel: GamePanelViewModel by viewModels()

    @Inject
    lateinit var gamePanelAdapter: GamePanelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamePanelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        gamePanelAdapter.setOnClickItem {
            if (!it.finished) {
                val intent = Intent(this@GamePanelActivity, RoundFormActivity::class.java).apply {
                    putExtra(IntentKey.GAME_ID, it.gameId)
                    putExtra(IntentKey.ROUND_ID, it.roundId)
                }
                startActivity(intent)
            }
        }

        binding.apply {
            val linearLayoutManager = LinearLayoutManager(this@GamePanelActivity)
            rvRoundsScores.adapter = gamePanelAdapter
            rvRoundsScores.layoutManager = linearLayoutManager
            rvRoundsScores.addItemDecoration(
                DividerItemDecoration(this@GamePanelActivity, linearLayoutManager.orientation)
            )
        }

        lifecycleScope.launchWhenStarted {
            launch {
                gamePanelViewModel.formEvent.collect {
                    when (it) {
                        is FormEvent.Error -> {
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                        }
                        is FormEvent.Delete -> {
                            Toast.makeText(this@GamePanelActivity, it.message, Toast.LENGTH_LONG).show()

                            if (it.finish) {
                                finish()
                            }
                        }
                    }
                }
            }

            launch {
                gamePanelViewModel.game.collect {
                    title = it.name
                }
            }

            launch {
                gamePanelViewModel.rounds.collect {
                    gamePanelAdapter.updateRounds(it)
                }
            }

            launch {
                gamePanelViewModel.roundsScores.collect {
                    gamePanelAdapter.updateRoundsScores(it)
                }
            }

            launch {
                gamePanelViewModel.gameScores.collect {
                    gamePanelAdapter.updateGameScores(it)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true;
            }
            R.id.form_delete -> {
                onDeletePlayer()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.form_menu, menu)

        return true
    }

    private fun onDeletePlayer() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.confirm_delete))
            setMessage(getString(R.string.msg_confirm_delete_game))
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                gamePanelViewModel.onDeleteGame()
            }
            setNegativeButton(getString(R.string.no), null)
            show()
        }
    }
}