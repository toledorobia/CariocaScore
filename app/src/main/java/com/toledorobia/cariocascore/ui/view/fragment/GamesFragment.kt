package com.toledorobia.cariocascore.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.toledorobia.cariocascore.core.IntentKey
import com.toledorobia.cariocascore.databinding.FragmentGamesBinding
import com.toledorobia.cariocascore.ui.adapter.GameAdapter
import com.toledorobia.cariocascore.ui.view.activity.GamePanelActivity
import com.toledorobia.cariocascore.ui.view.activity.GameWizardActivity
import com.toledorobia.cariocascore.ui.viewmodel.GamesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class GamesFragment : Fragment() {

    private val gamesViewModel: GamesViewModel by activityViewModels()
    private lateinit var binding: FragmentGamesBinding

    @Inject
    lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameAdapter.setOnClickItem {
            val intent = Intent(context, GamePanelActivity::class.java).apply {
                putExtra(IntentKey.GAME_ID, it.id)
            }
            startActivity(intent)
        }

        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvGames.adapter = gameAdapter
        binding.rvGames.layoutManager = linearLayoutManager
        binding.rvGames.addItemDecoration(
            DividerItemDecoration(context, linearLayoutManager.orientation)
        )

        lifecycleScope.launchWhenStarted {
            gamesViewModel.games.collectLatest {
                gameAdapter.updateItems(it)
            }
        }


        binding.faGameAdd.setOnClickListener {
            val intent = Intent(context, GameWizardActivity::class.java)
            startActivity(intent)
        }
    }
}