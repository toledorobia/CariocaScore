package com.toledorobia.cariocascore.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.toledorobia.cariocascore.databinding.ActivityPlayerFormBinding
import com.toledorobia.cariocascore.domain.models.InvalidPlayerException
import com.toledorobia.cariocascore.ui.viewmodel.PlayerFormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerFormBinding
    private val playerFormViewModel: PlayerFormViewModel by viewModels()
    private lateinit var nameTextWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btFormPlayerSave.setOnClickListener {
            try {
                playerFormViewModel.onSavePlayer()
                Toast.makeText(this, "Player saved!", Toast.LENGTH_LONG).show()
                finish();
            } catch (ex: InvalidPlayerException) {
                Toast.makeText(this, ex.message.toString(), Toast.LENGTH_LONG).show()
            }
        }

        nameTextWatcher = binding.etFormPlayerName.addTextChangedListener {
            playerFormViewModel.setName(it.toString())
        }

        playerFormViewModel.loading.observe(this, Observer {
            binding.btFormPlayerSave.isEnabled = !it
            binding.etFormPlayerName.isEnabled = !it
        })
    }

    override fun onDestroy() {
        binding.etFormPlayerName.removeTextChangedListener(nameTextWatcher)
        super.onDestroy()
    }
}