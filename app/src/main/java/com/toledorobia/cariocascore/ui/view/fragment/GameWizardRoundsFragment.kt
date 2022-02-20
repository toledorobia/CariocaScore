package com.toledorobia.cariocascore.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.toledorobia.cariocascore.databinding.FragmentGameWizardRoundsBinding
import com.toledorobia.cariocascore.ui.adapter.GameWizardRoundsAdapter
import com.toledorobia.cariocascore.ui.viewmodel.GameWizardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class GameWizardRoundsFragment : Fragment() {

    private lateinit var binding: FragmentGameWizardRoundsBinding
    private val gameWizardViewModel: GameWizardViewModel by activityViewModels()

    @Inject
    lateinit var gameRoundsAdapter: GameWizardRoundsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameWizardRoundsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameRoundsAdapter.setOnCheckedRoundLister { position, checked ->
            gameWizardViewModel.setRound(position, checked)
        }

        binding.rvGameRounds.adapter = gameRoundsAdapter
        binding.rvGameRounds.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launchWhenStarted {
            gameWizardViewModel.rounds.collect {
                gameRoundsAdapter.updateItems(it ?: emptyList())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameWizardRoundsFragment()
    }
}