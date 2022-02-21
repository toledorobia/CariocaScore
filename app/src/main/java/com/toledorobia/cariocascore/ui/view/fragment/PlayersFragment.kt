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
import com.toledorobia.cariocascore.databinding.FragmentPlayersBinding
import com.toledorobia.cariocascore.ui.adapter.PlayersAdapter
import com.toledorobia.cariocascore.ui.view.activity.PlayerFormActivity
import com.toledorobia.cariocascore.ui.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class PlayersFragment : Fragment() {

    private lateinit var binding: FragmentPlayersBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    @Inject
    lateinit var playersAdapter: PlayersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playersAdapter = PlayersAdapter(
            context,
            onClickItem = {
                val intent = Intent(context, PlayerFormActivity::class.java).apply {
                    putExtra(IntentKey.PLAYER_ID, it.id)
                }

                startActivity(intent)
            }
        )

        val linearLayoutManager = LinearLayoutManager(context)

        binding.rvPlayers.adapter = playersAdapter
        binding.rvPlayers.layoutManager = linearLayoutManager
        binding.rvPlayers.addItemDecoration(
            DividerItemDecoration(context, linearLayoutManager.orientation)
        )

        lifecycleScope.launchWhenStarted {
            mainViewModel.players.collectLatest {
                playersAdapter.updateItems(it)
            }
        }

        binding.faPlayerAdd.setOnClickListener {
            val intent = Intent(context, PlayerFormActivity::class.java)
            startActivity(intent)
        }
    }
}