package com.toledorobia.cariocascore.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.toledorobia.cariocascore.databinding.FragmentGameRoundsBinding
import com.toledorobia.cariocascore.ui.viewmodel.GameWizardViewModel

class GameRoundsFragment : Fragment() {

    private lateinit var binding: FragmentGameRoundsBinding
    private val gameWizardViewModel: GameWizardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameRoundsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameRoundsFragment()
    }
}