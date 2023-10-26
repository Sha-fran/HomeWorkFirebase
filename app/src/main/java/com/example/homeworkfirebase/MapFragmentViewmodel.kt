package com.example.homeworkfirebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapFragmentViewmodel:ViewModel() {
    private val repo = Repository()
    private val _uiState =MutableLiveData<UiState>(UiState.EmptyRoute)
    val uiState:LiveData<UiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getRoute()
            withContext(Dispatchers.Main) {
                response.body().let {
                    _uiState.postValue(UiState.FilledRoute(it))
                }
            }
        }
    }
}

sealed class UiState {
    data object EmptyRoute:UiState()
    class FilledRoute(val route:DirectionResponce?):UiState()
}
