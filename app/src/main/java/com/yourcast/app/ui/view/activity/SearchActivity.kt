package com.yourcast.app.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.yourcast.app.data.model.City
import com.yourcast.app.data.model.Direction
import com.yourcast.app.data.remote.ApiResult
import com.yourcast.app.databinding.ActivitySearchBinding
import com.yourcast.app.ui.view.adapter.SearchAdapter
import com.yourcast.app.ui.viewModel.CityViewModel
import com.yourcast.app.ui.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), SearchAdapter.AdapterCallback {

    private companion object {
        const val TAG = "SearchActivity"
    }

    private var direction: Direction? = null

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val cityViewModel: CityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SearchAdapter(this)
        binding.recycler.adapter = adapter

        binding.editSearch.doAfterTextChanged { editable ->
            searchViewModel.search(editable.toString())
        }

        searchViewModel.result.observe(this) { result ->
            when (result) {
                is ApiResult.Success -> {
                    adapter.setData(result.data)
                }
                is ApiResult.Error -> {
                    val error = result.exception.message
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error: $error")
                    adapter.setData(emptyList())
                }
            }
        }

        cityViewModel.result.observe(this, { result ->
            when (result) {
                is ApiResult.Success -> {
                    result.data.let { city ->
                        city.name = direction!!.name
                        city.country = direction!!.country
                        cityViewModel.saveCity(result.data)
                        finish()
                    }
                }
                is ApiResult.Error -> {
                    val error = result.exception.message
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error: $error")
                }
            }
        })
    }

    override fun onItemClicked(direction: Direction) {
        this.direction = direction
        cityViewModel.oneCall(direction.lat, direction.lon)
    }
}