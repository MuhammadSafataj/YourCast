package com.yourcast.app.ui.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.yourcast.app.R
import com.yourcast.app.data.model.City
import com.yourcast.app.databinding.FragmentCityBinding
import com.yourcast.app.ui.view.adapter.DailyAdapter
import com.yourcast.app.util.Extensions.fromJson
import com.yourcast.app.util.Extensions.json
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class CityFragment : Fragment() {

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

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

        binding.toolbar.inflateMenu(R.menu.menu_city)

        // Convert timestamp to readable date.
        val date = Date(city.current.dt * 1000)
        val format = SimpleDateFormat("EE, DD MMMM hh:mm a", Locale.getDefault())

        binding.toolbar.title = "${city.name}, ${city.country}"
        binding.toolbar.subtitle = format.format(date)


        binding.textTemperature.text = String.format("%s°", city.current.temp.roundToInt())
        binding.textDescription.text = city.current.weather[0].description
        binding.textHumidity.text = city.current.humidity.toString()
        binding.textUvi.text = city.current.uvi.toString()
        binding.textVisibility.text = city.current.visibility.toString()
        binding.textWind.text = city.current.wind_speed.toString()

        val adapter = DailyAdapter(requireContext())
        adapter.setData(city.daily!!)
        binding.recyclerDaily.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}