package com.yourcast.app.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yourcast.app.databinding.FragmentCitiesBinding
import com.yourcast.app.ui.view.adapter.CityAdapter
import com.yourcast.app.ui.viewModel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CityAdapter()
        binding.recyclerCities.adapter = adapter

        binding.fabAdd.setOnClickListener {
            val action = CitiesFragmentDirections.actionItemCitiesToFragmentSearch()
            findNavController().navigate(action)
        }

        viewModel.cities.observe(viewLifecycleOwner) { cities ->
            adapter.setData(cities)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}