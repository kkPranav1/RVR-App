package com.matrimony.rvrmatrimony.uicode.onboarding

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.matrimony.rvrmatrimony.basemodule.BaseViewModel
import com.matrimony.rvrmatrimony.data.remote.ApiResponse
import com.matrimony.rvrmatrimony.data.remote.CarResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TestScreenViewModel @Inject constructor(
    // private val savedStateHandle: SavedStateHandle,
    private val repository: OnBoardingRepository,
) : BaseViewModel() {
    private val _carData = MutableStateFlow<ApiResponse<CarResponse>>(ApiResponse.Loading())
    val carData: StateFlow<ApiResponse<CarResponse>> = _carData.asStateFlow()

    fun getCarById(id: Int = 1) {
        viewModelScope.launch {
            repository.getCarById(id).collect { response ->
                _carData.value = response
            }
        }
    }
}