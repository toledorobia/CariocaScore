package com.toledorobia.cariocascore.ui.view.fragment

import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.toledorobia.cariocascore.core.Utils
import com.toledorobia.cariocascore.databinding.FragmentGameWizardDataBinding
import com.toledorobia.cariocascore.ui.viewmodel.GameWizardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameWizardDataFragment : Fragment() {
    private lateinit var binding: FragmentGameWizardDataBinding
    private val gameWizardViewModel: GameWizardViewModel by activityViewModels()
    private lateinit var nameTextWatcher: TextWatcher

    @Inject
    lateinit var utils: Utils

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameWizardDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTextWatcher = binding.etGameName.addTextChangedListener {
            gameWizardViewModel.setGameName(it.toString())
        }

        binding.etGameName.setText(utils.defaultGameName())
    }

    override fun onDestroy() {
        binding.etGameName.removeTextChangedListener(nameTextWatcher)
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameWizardDataFragment()
    }
}