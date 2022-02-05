package com.toledorobia.cariocascore.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.snackbar.Snackbar
import com.toledorobia.cariocascore.databinding.ActivityGameWizardBinding
import com.toledorobia.cariocascore.ui.event.FormEvent
import com.toledorobia.cariocascore.ui.view.fragment.GameDataFragment
import com.toledorobia.cariocascore.ui.view.fragment.GamePlayersFragment
import com.toledorobia.cariocascore.ui.view.fragment.GameRoundsFragment
import com.toledorobia.cariocascore.ui.viewmodel.GameWizardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class GameWizardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameWizardBinding
    private val gameWizardViewModel: GameWizardViewModel by viewModels()
    private lateinit var wizardCollectionAdapter: FragmentStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameWizardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        wizardCollectionAdapter = WizardCollectionAdapter(this)

        binding.apply {
            vpWizard.adapter = wizardCollectionAdapter

            vpWizard.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    gameWizardViewModel.onChangePage(position, wizardCollectionAdapter.itemCount)
                }
            })

            btPrev.setOnClickListener {
                binding.vpWizard.currentItem = binding.vpWizard.currentItem - 1
            }

            btNext.setOnClickListener {
                binding.vpWizard.currentItem = binding.vpWizard.currentItem + 1
            }

            btCancel.setOnClickListener {
                finish()
            }

            btSave.setOnClickListener {
                gameWizardViewModel.saveGame()
            }
        }

        gameWizardViewModel.enablePrev.observe(this, Observer {
            binding.btPrev.isEnabled = it
        })

        gameWizardViewModel.enableNext.observe(this, Observer {
            binding.btNext.isEnabled = it
        })

        gameWizardViewModel.visibleSave.observe(this, Observer {
            binding.btNext.visibility = if (it) {
                View.GONE
            }
            else {
                View.VISIBLE
            }

            binding.btSave.visibility = if (it) {
                View.VISIBLE
            }
            else {
                View.GONE
            }
        })

        gameWizardViewModel.visibleCancel.observe(this, Observer {
            binding.btPrev.visibility = if (it) {
                View.GONE
            }
            else {
                View.VISIBLE
            }

            binding.btCancel.visibility = if (it) {
                View.VISIBLE
            }
            else {
                View.GONE
            }
        })

        lifecycleScope.launchWhenStarted {
            gameWizardViewModel.formEvent.collect {
                when (it) {
                    is FormEvent.Success -> {
                        Toast.makeText(this@GameWizardActivity, it.message, Toast.LENGTH_LONG).show()

                        if (it.finish) {
                            finish()
                        }
                    }
                    is FormEvent.Error -> {
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                    }
                    is FormEvent.Submitting -> {
                        binding.apply {
                            btCancel.isEnabled = !it.loading
                            btSave.isEnabled = !it.loading
                            btNext.isEnabled = !it.loading
                            btPrev.isEnabled = !it.loading
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

    inner class WizardCollectionAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 3;
        }

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> GameDataFragment.newInstance()
                1 -> GamePlayersFragment.newInstance()
                2 -> GameRoundsFragment.newInstance()
                else -> throw IllegalStateException("Bad fragment position.")
            }
        }
    }
}