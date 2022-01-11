package com.yourcast.app.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourcast.app.data.model.Current
import com.yourcast.app.data.network.Response
import com.yourcast.app.data.repository.CurrentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CurrentRepository
): ViewModel() {

    val result = MutableLiveData<Response<Current>>()

    var city = ""

    fun getCurrent() {
        viewModelScope.launch {
            repository.getCurrent(city).collect {
                result.postValue(it)
            }
        }
    }

}