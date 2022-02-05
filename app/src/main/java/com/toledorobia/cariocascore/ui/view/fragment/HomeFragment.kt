package com.toledorobia.cariocascore.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.toledorobia.cariocascore.core.IntentKey
import com.toledorobia.cariocascore.databinding.FragmentHomeBinding
import com.toledorobia.cariocascore.ui.adapter.GameAdapter
import com.toledorobia.cariocascore.ui.view.activity.GamePanelActivity
import com.toledorobia.cariocascore.ui.view.activity.GameWizardActivity
import com.toledorobia.cariocascore.ui.view.activity.PlayerFormActivity
import com.toledorobia.cariocascore.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameAdapter = GameAdapter(
            onClickItem = {
                val intent = Intent(context, GamePanelActivity::class.java).apply {
                    putExtra(IntentKey.GAME_ID, it.id)
                }
                startActivity(intent)
            }
        )

        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvGames.adapter = gameAdapter
        binding.rvGames.layoutManager = linearLayoutManager
        binding.rvGames.addItemDecoration(
            DividerItemDecoration(context, linearLayoutManager.orientation)
        )

        homeViewModel.games.observe(viewLifecycleOwner, Observer {
            gameAdapter.updateItems(it)
        })

        binding.faGameAdd.setOnClickListener {
            val intent = Intent(context, GameWizardActivity::class.java)
            startActivity(intent)
        }
    }
}