package com.yourcast.app.ui.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide

import com.yourcast.app.R
import com.yourcast.app.data.model.City
import com.yourcast.app.data.remote.ApiResult
import com.yourcast.app.databinding.FragmentCityBinding
import com.yourcast.app.ui.view.adapter.DailyAdapter
import com.yourcast.app.ui.view.adapter.HourlyAdapter
import com.yourcast.app.ui.viewModel.CityViewModel
import com.yourcast.app.util.Constants
import com.yourcast.app.util.Extensions.fromJson
import com.yourcast.app.util.Extensions.json
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@AndroidEntryPoint
class CityFragment : Fragment() {

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    private val cityViewModel: CityViewModel by viewModels()

    private lateinit var city: City

    companion object {
        private const val ARG_CITY = "city"

        @JvmStatic
        fun newInstance(city: City) = CityFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CITY, city.json())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(ARG_CITY)?.fromJson() ?: return
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityViewModel.result.observe(viewLifecycleOwner) { apiResult ->
            when(apiResult) {
                is ApiResult.GenericError -> {}
                is ApiResult.Loading -> {}
                is ApiResult.NetworkError -> {}
                is ApiResult.Success -> {
                    city.apply {
                        current = apiResult.data.current
                        daily = apiResult.data.daily
                        hourly = apiResult.data.hourly
                        minutely = apiResult.data.minutely
                    }
                    cityViewModel.update(city)
                }
            }
        }

        binding.toolbar.inflateMenu(R.menu.menu_city)
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.item_delete -> cityViewModel.delete(city)
                R.id.item_refresh -> refresh()
            }
            true
        }

        setData()

        val hourlyAdapter = HourlyAdapter()
        hourlyAdapter.setData(city.hourly!!)
        binding.recyclerHourly.adapter = hourlyAdapter

        val dailyAdapter = DailyAdapter(requireContext())
        dailyAdapter.setData(city.daily!!)
        binding.recyclerDaily.adapter = dailyAdapter


    }

    private fun setData() {
        // Convert timestamp to readable date.
        val date = Date(city.current.dt * 1000)
        val format = SimpleDateFormat("EE, D MMMM hh:mm a", Locale.US)

        binding.toolbar.title = "${city.name}, ${city.country}"
        binding.toolbar.subtitle = format.format(date)


        binding.textTemperature.text = String.format("%s°", city.current.temp.roundToInt())
        binding.textDescription.text = city.current.weather[0].description
        binding.textHumidity.text = city.current.humidity.toString()
        binding.textUvi.text = city.current.uvi.toString()
        binding.textVisibility.text = city.current.visibility.toString()
        binding.textWind.text = city.current.wind_speed.toString()

        val type = city.current.weather[0].id.toString()

        when {
            type.startsWith("2") -> {
                Glide.with(requireContext()).load(R.drawable.thunderstorm)
                    .into(binding.background)
            }
            type.startsWith("3") -> {
                Glide.with(requireContext()).load(R.drawable.drizzle).into(binding.background)
            }
            type.startsWith("5") -> {
                Glide.with(requireContext()).load(R.drawable.rain).into(binding.background)
            }
            type.startsWith("6") -> {
                Glide.with(requireContext()).load(R.drawable.snow).into(binding.background)
            }
            type.startsWith("8") -> {
                if (type == "800") {
                    Glide.with(requireContext()).load(R.drawable.sunflower)
                        .into(binding.background)
                } else {
                    Glide.with(requireContext()).load(R.drawable.cloud)
                        .into(binding.background)
                }
            }
        }
    }

    private fun refresh() {
        cityViewModel.oneCall(city.lat, city.lon)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}