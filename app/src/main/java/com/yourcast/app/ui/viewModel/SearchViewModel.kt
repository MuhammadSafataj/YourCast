package com.yourcast.app.ui.viewModel

import androidx.lifecycle.*
import com.yourcast.app.data.model.Direction
import com.yourcast.app.data.remote.ApiResult
import com.yourcast.app.data.remote.ApiService
import com.yourcast.app.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    val result = MutableLiveData<ApiResult<List<Direction>>>()

    fun search(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResult = api.direct(value, 10, Constants.OPEN_WEATHER_MAP_API_KEY)
                result.postValue(ApiResult.Success(apiResult))
            } catch (exception: Exception) {
                result.postValue(ApiResult.Error(exception))
            }

        }
    }

}