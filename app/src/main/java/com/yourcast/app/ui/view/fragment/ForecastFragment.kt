package com.yourcast.app.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yourcast.app.R
import com.yourcast.app.data.model.City
import com.yourcast.app.databinding.FragmentForecastBinding
import com.yourcast.app.ui.viewModel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private var cities: List<City> = emptyList()

    // ViewModel
    private val viewModel: CityViewModel by viewModels()

    // ViewBinding
    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PagerAdapter(this)
        binding.pager.adapter = adapter

        viewModel.cities.observe(viewLifecycleOwner) {
            cities = it
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
       _binding = null
    }

    private inner class PagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = cities.size

        override fun createFragment(position: Int): Fragment {
            return CityFragment.newInstance(cities[position])
        }
    }
}