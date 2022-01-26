package com.toledorobia.cariocascore.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.toledorobia.cariocascore.databinding.FragmentHomeBinding
import com.toledorobia.cariocascore.ui.adapter.TestAdapter
import com.toledorobia.cariocascore.ui.view.GameFormActivity
import com.toledorobia.cariocascore.ui.view.GameWizardActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var testAdapter: TestAdapter;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getTestData()

        testAdapter = TestAdapter()
        binding.rvGames.adapter = testAdapter
        binding.rvGames.layoutManager = LinearLayoutManager(context)

        homeViewModel.testData.observe(viewLifecycleOwner, Observer {
            testAdapter.updateItems(it)
        })

        binding.faGameAdd.setOnClickListener {
            val intent = Intent(context, GameWizardActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}