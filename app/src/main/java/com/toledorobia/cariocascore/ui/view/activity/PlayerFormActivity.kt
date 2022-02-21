package com.toledorobia.cariocascore.ui.view.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.toledorobia.cariocascore.R
import com.toledorobia.cariocascore.databinding.ActivityPlayerFormBinding
import com.toledorobia.cariocascore.ui.event.FormEvent
import com.toledorobia.cariocascore.ui.viewmodel.PlayerFormViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PlayerFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerFormBinding
    private val playerFormViewModel: PlayerFormViewModel by viewModels()
    private lateinit var nameTextWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = getString(R.string.title_form_new, getString(R.string.player))

        binding.btPlayerFormSave.setOnClickListener {
            playerFormViewModel.onSavePlayer()
        }

        binding.btPlayerFormCancel.setOnClickListener {
            finish()
        }

        nameTextWatcher = binding.etFormPlayerName.addTextChangedListener {
            playerFormViewModel.setName(it.toString())
        }

        lifecycleScope.launchWhenStarted {
            playerFormViewModel.player.observe(this@PlayerFormActivity) {
                if (it != null) {
                    title = getString(R.string.title_form_edit, getString(R.string.player))

                    binding.etFormPlayerName.setText(it.name)
                    invalidateOptionsMenu()
                }
            }

            playerFormViewModel.formEvent.collect {
                when (it) {
                    is FormEvent.Error -> {
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                    }
                    is FormEvent.Success -> {
                        Toast.makeText(this@PlayerFormActivity, it.message, Toast.LENGTH_LONG).show()

                        if (it.finish) {
                            finish()
                        }
                    }
                    is FormEvent.Delete -> {
                        Toast.makeText(this@PlayerFormActivity, it.message, Toast.LENGTH_LONG).show()

                        if (it.finish) {
                            finish()
                        }
                    }
                    is FormEvent.Submitting -> {
                        binding.btPlayerFormSave.isEnabled = !it.loading
                        binding.etFormPlayerName.isEnabled = !it.loading
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        binding.etFormPlayerName.removeTextChangedListener(nameTextWatcher)
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.form_menu, menu)

        if (playerFormViewModel.player.value == null) {
            menu.findItem(R.id.form_delete).apply {
                isVisible = false
            }
        }

        return true
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

    private fun onDeletePlayer() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.confirm_delete))
            setMessage(getString(R.string.msg_confirm_delete_player))
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                playerFormViewModel.onDeletePlayer()
            }
            setNegativeButton(getString(R.string.no), null)
            show()
        }
    }
}