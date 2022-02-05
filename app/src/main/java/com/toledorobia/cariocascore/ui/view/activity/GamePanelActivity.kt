package com.toledorobia.cariocascore.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.lifecycle.Observer
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.databinding.ActivityGamePanelBinding
import com.toledorobia.cariocascore.databinding.ActivityGameWizardBinding
import com.toledorobia.cariocascore.domain.models.GameRoundPlayerModel
import com.toledorobia.cariocascore.domain.models.PlayerModel
import com.toledorobia.cariocascore.domain.models.RoundModel
import com.toledorobia.cariocascore.ui.viewmodel.GamePanelViewModel
import com.toledorobia.cariocascore.ui.viewmodel.GameWizardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamePanelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGamePanelBinding
    private val gamePanelViewModel: GamePanelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamePanelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        gamePanelViewModel.players.observe(this) {
            setHeaders(it)
        }

        gamePanelViewModel.rounds.observe(this) {
            setResults(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true;
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setHeaders(players: List<PlayerModel>) {
        binding.tlGameStatus.apply {
            if (childCount > 0) {
                removeViewAt(0)
            }

            val row = TableRow(this@GamePanelActivity)
            var col = TextView(this@GamePanelActivity)
            col.text = "Rounds"
            row.addView(col)

            players.forEach {
                col = TextView(this@GamePanelActivity)
                col.text = it.name
                row.addView(col)
            }

            addView(row, 0)
        }
    }

    private fun setResults(rounds: Map<RoundModel, List<GameRoundPlayerModel>>) {
        binding.tlGameStatus.apply {
            if (childCount > 1) {
                removeViews(1, childCount)
            }

            rounds.forEach { entry ->
                var row = TableRow(this@GamePanelActivity)
                var col = TextView(this@GamePanelActivity)
                col.text = entry.key.name
                row.addView(col)

                entry.value.forEach {
                    col = TextView(this@GamePanelActivity)
                    col.text = it.score.toString()
                    row.addView(col)
                }

                addView(row)
            }
        }
    }
}