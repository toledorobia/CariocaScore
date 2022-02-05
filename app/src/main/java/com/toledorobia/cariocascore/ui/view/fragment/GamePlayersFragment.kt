package com.toledorobia.cariocascore.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.toledorobia.cariocascore.core.Logger
import com.toledorobia.cariocascore.databinding.FragmentGamePlayersBinding
import com.toledorobia.cariocascore.ui.adapter.GamePlayersAdapter
import com.toledorobia.cariocascore.ui.viewmodel.GameWizardViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@AndroidEntryPoint
class GamePlayersFragment : Fragment() {
    private lateinit var binding: FragmentGamePlayersBinding
    private val gameWizardViewModel: GameWizardViewModel by activityViewModels()
    private lateinit var gamePlayersAdapter: GamePlayersAdapter

    @Inject lateinit var logger: Logger

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGamePlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamePlayersAdapter = GamePlayersAdapter(
            onCheckedPlayerListener = { player, checked ->
                gameWizardViewModel.setPlayer(player, checked)
            }
        )

        binding.rvGamePlayers.adapter = gamePlayersAdapter
        binding.rvGamePlayers.layoutManager = LinearLayoutManager(context)

        gameWizardViewModel.players.observe(viewLifecycleOwner, Observer {
            gamePlayersAdapter.updateItems(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = GamePlayersFragment()
    }
}