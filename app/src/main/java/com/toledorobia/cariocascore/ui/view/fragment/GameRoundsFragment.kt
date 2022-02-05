package com.toledorobia.cariocascore.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.toledorobia.cariocascore.databinding.FragmentGameRoundsBinding
import com.toledorobia.cariocascore.ui.adapter.GameRoundsAdapter
import com.toledorobia.cariocascore.ui.viewmodel.GameWizardViewModel

class GameRoundsFragment : Fragment() {

    private lateinit var binding: FragmentGameRoundsBinding
    private val gameWizardViewModel: GameWizardViewModel by activityViewModels()
    private lateinit var gameRoundsAdapter: GameRoundsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameRoundsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameRoundsAdapter = GameRoundsAdapter(
            onCheckedRoundListener = { position, checked ->
                gameWizardViewModel.setRound(position, checked)
            }
        )

        binding.rvGameRounds.adapter = gameRoundsAdapter
        binding.rvGameRounds.layoutManager = LinearLayoutManager(context)

        gameWizardViewModel.rounds.observe(viewLifecycleOwner, Observer {
            gameRoundsAdapter.updateItems(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameRoundsFragment()
    }
}