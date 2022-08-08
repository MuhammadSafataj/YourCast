package com.yourcast.app.ui.viewModel

import androidx.lifecycle.*
import com.yourcast.app.data.model.City
import com.yourcast.app.data.remote.ApiResult
import com.yourcast.app.data.repository.CityRepository
import com.yourcast.app.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CityRepository
) : ViewModel() {

    val result = MutableLiveData<ApiResult<City>>()
    val cities: LiveData<List<City>> = repository.cities.asLiveData()

    fun oneCall(lat: Double, lon: Double) {
        viewModelScope.launch {
            repository.oneCall(lat, lon, Constants.OPEN_WEATHER_MAP_API_KEY).collect { response ->
                result.postValue(response)
            }
        }
    }

    fun save(city: City) {
        viewModelScope.launch {
            repository.save(city)
        }
    }

    fun delete(city: City) {
        viewModelScope.launch {
            repository.delete(city)
        }
    }

    fun update(city: City) {
        viewModelScope.launch {
            repository.update(city)
        }
    }
}