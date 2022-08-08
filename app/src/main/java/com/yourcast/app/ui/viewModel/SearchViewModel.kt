package com.yourcast.app.ui.viewModel

import androidx.lifecycle.*
import com.yourcast.app.data.model.Direction
import com.yourcast.app.data.remote.ApiResult
import com.yourcast.app.data.remote.ApiService
import com.yourcast.app.data.repository.SearchRepository
import com.yourcast.app.di.qualifier.IoDispatcher
import com.yourcast.app.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _apiResult: MutableLiveData<ApiResult<List<Direction>>> = MutableLiveData()
    val apiResult: LiveData<ApiResult<List<Direction>>> get() = _apiResult

    fun search(value: String) {
        viewModelScope.launch {
            repository.search(value).collect {
                _apiResult.postValue(it)
            }
        }
    }
}