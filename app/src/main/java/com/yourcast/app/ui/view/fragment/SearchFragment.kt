package com.yourcast.app.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yourcast.app.data.model.Direction
import com.yourcast.app.data.remote.ApiResult
import com.yourcast.app.databinding.FragmentSearchBinding
import com.yourcast.app.ui.view.adapter.SearchAdapter
import com.yourcast.app.ui.viewModel.CityViewModel
import com.yourcast.app.ui.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchAdapter.AdapterCallback {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModels()
    private val cityViewModel: CityViewModel by viewModels()

    private var direction: Direction? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SearchAdapter(this)
        binding.recycler.adapter = adapter

        binding.iconBack.setOnClickListener { findNavController().popBackStack() }
        binding.editSearch.doAfterTextChanged { searchViewModel.search(it.toString()) }

        searchViewModel.apiResult.observe(viewLifecycleOwner) { apiResult ->
            when (apiResult) {
                is ApiResult.GenericError -> {
                    val message = "${apiResult.code} ${apiResult.message}"
                    binding.textMessageTitle.text = message
                    binding.layoutShimmer.visibility = View.GONE
                    binding.recycler.visibility = View.GONE
                    binding.layoutMessage.visibility = View.VISIBLE
                    adapter.setData(emptyList())
                }
                is ApiResult.Loading -> {
                    binding.layoutShimmer.visibility = View.VISIBLE
                    binding.recycler.visibility = View.GONE
                    binding.layoutMessage.visibility = View.GONE
                }
                is ApiResult.NetworkError -> {
                    binding.textMessageTitle.text = "Error"
                    binding.layoutShimmer.visibility = View.GONE
                    binding.recycler.visibility = View.GONE
                    binding.layoutMessage.visibility = View.VISIBLE
                    adapter.setData(emptyList())
                }
                is ApiResult.Success -> {

                    adapter.setData(apiResult.data)

                    if(apiResult.data.isEmpty()) {
                        binding.textMessageTitle.text = "No Cities Found"
                        binding.layoutShimmer.visibility = View.GONE
                        binding.recycler.visibility = View.GONE
                        binding.layoutMessage.visibility = View.VISIBLE

                    } else {
                        binding.layoutShimmer.visibility = View.GONE
                        binding.recycler.visibility = View.VISIBLE
                        binding.layoutMessage.visibility = View.GONE
                    }
                }
            }
        }

        cityViewModel.result.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResult.Success -> {
                    result.data.let { city ->
                        city.name = direction!!.name
                        city.country = direction!!.country
                        cityViewModel.save(result.data)
                        findNavController().popBackStack()
                    }
                }
                is ApiResult.GenericError -> {}
                is ApiResult.Loading -> {}
                is ApiResult.NetworkError -> {}
            }
        }
    }

    override fun onItemClicked(direction: Direction) {
        this.direction = direction
        cityViewModel.oneCall(direction.lat, direction.lon)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}