package com.toledorobia.cariocascore.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.core.Utils
import com.toledorobia.cariocascore.databinding.ActivityGamePanelBinding
import com.toledorobia.cariocascore.databinding.ActivityRoundFormBinding
import com.toledorobia.cariocascore.ui.adapter.RoundFormAdapter
import com.toledorobia.cariocascore.ui.event.FormEvent
import com.toledorobia.cariocascore.ui.viewmodel.GamePanelViewModel
import com.toledorobia.cariocascore.ui.viewmodel.RoundFormViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

@AndroidEntryPoint
class RoundFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoundFormBinding
    private val roundFormViewModel: RoundFormViewModel by viewModels()
    private lateinit var roundFormAdapter: RoundFormAdapter
    @Inject
    lateinit var utils: Utils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoundFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        roundFormAdapter = RoundFormAdapter(
            onSetScoreListener = { id, score ->
                roundFormViewModel.setScore(id, score)
            }
        )

        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvRoundForm.apply {
            adapter = roundFormAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(
                DividerItemDecoration(this@RoundFormActivity, linearLayoutManager.orientation)
            )
        }

        binding.btRoundBack.setOnClickListener {
            finish()
        }

        binding.btRoundSave.setOnClickListener {
            onSaveRound()
        }

        roundFormViewModel.results.observe(this) {
            roundFormAdapter.updateItems(it)
        }

        lifecycleScope.launchWhenStarted {
            launch {
                roundFormViewModel.round.collectLatest {
                    title = utils.getStringByName(it.code)
                }
            }

            launch {
                roundFormViewModel.formEvent.collect {
                    when (it) {
                        is FormEvent.Success -> {
                            Toast.makeText(this@RoundFormActivity, it.message, Toast.LENGTH_LONG).show()

                            if (it.finish) {
                                 finish()
                            }
                        }
                        is FormEvent.Error -> {
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                        }
                        is FormEvent.Submitting -> {
                            binding.apply {
                                btRoundBack.isEnabled = !it.loading
                                btRoundSave.isEnabled = !it.loading
                            }
                        }
                    }
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
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onSaveRound() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.confirm_scores))
            setMessage(getString(R.string.msg_confirm_scores))
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                roundFormViewModel.saveRound()
            }
            setNegativeButton(R.string.no, null)
            show()
        }
    }
}