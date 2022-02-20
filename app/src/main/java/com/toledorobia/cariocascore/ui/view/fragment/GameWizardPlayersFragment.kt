package com.toledorobia.cariocascore.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.toledorobia.cariocascore.core.Logger
import com.toledorobia.cariocascore.databinding.FragmentGameWizardPlayersBinding
import com.toledorobia.cariocascore.ui.adapter.GameWizardPlayersAdapter
import com.toledorobia.cariocascore.ui.viewmodel.GameWizardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class GameWizardPlayersFragment : Fragment() {
    private lateinit var binding: FragmentGameWizardPlayersBinding
    private val gameWizardViewModel: GameWizardViewModel by activityViewModels()
    private lateinit var gamePlayersAdapter: GameWizardPlayersAdapter

    @Inject lateinit var logger: Logger

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameWizardPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamePlayersAdapter = GameWizardPlayersAdapter(
            onCheckedPlayerListener = { player, checked ->
                gameWizardViewModel.setPlayer(player, checked)
            }
        )

        binding.rvGamePlayers.adapter = gamePlayersAdapter
        binding.rvGamePlayers.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launchWhenStarted {
            gameWizardViewModel.players.collect {
                gamePlayersAdapter.updateItems(it ?: emptyList())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameWizardPlayersFragment()
    }
}