package com.toledorobia.cariocascore.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.databinding.FragmentPlayersBinding
import com.toledorobia.cariocascore.ui.adapter.GameRoundsAdapter
import com.toledorobia.cariocascore.ui.adapter.PlayersAdapter
import com.toledorobia.cariocascore.ui.view.activity.GameWizardActivity
import com.toledorobia.cariocascore.ui.view.activity.PlayerFormActivity
import com.toledorobia.cariocascore.ui.viewmodel.PlayersViewModel

class PlayersFragment : Fragment() {

    private lateinit var binding: FragmentPlayersBinding
    private val playersViewModel: PlayersViewModel by activityViewModels()
    private lateinit var playersAdapter: PlayersAdapter

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
            onEditPlayer = {
                Toast.makeText(context, "edit player", Toast.LENGTH_LONG).show()
            },
            onDeletePlayer = {
                Toast.makeText(context, "delete player", Toast.LENGTH_LONG).show()
            },
            onStatsPlayer = {
                Toast.makeText(context, "stats player", Toast.LENGTH_LONG).show()
            },
            onClickItem = {
                //Toast.makeText(context, "click player ${it.name}", Toast.LENGTH_LONG).show()
            }
        )

        val linearLayoutManager = LinearLayoutManager(context)

        binding.rvPlayers.adapter = playersAdapter
        binding.rvPlayers.layoutManager = linearLayoutManager
        binding.rvPlayers.addItemDecoration(
            DividerItemDecoration(context, linearLayoutManager.orientation)
        )


        playersViewModel.players.observe(viewLifecycleOwner, Observer {
            playersAdapter.updateItems(it)
        })

        binding.faPlayerAdd.setOnClickListener {
            val intent = Intent(context, PlayerFormActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
//        playersViewModel.onResume()
    }


}