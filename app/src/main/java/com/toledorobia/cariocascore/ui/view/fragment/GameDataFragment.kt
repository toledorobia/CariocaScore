package com.toledorobia.cariocascore.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.toledorobia.cariocascore.databinding.FragmentGameDataBinding
import com.toledorobia.cariocascore.ui.viewmodel.GameWizardViewModel

class GameDataFragment : Fragment() {
    private lateinit var binding: FragmentGameDataBinding
    private val gameWizardViewModel: GameWizardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameDataFragment()
    }
}