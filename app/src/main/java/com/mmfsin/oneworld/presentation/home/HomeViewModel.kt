package com.mmfsin.oneworld.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmfsin.oneworld.domain.usecases.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeStates())
    val uiState: StateFlow<HomeStates> = _uiState

    init {
        getEvents()
    }

    fun getEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            val response = getEventsUseCase()
            response?.let { r ->
                _uiState.update {
                    it.copy(
                        events = r,
                        isLoading = false
                    )
                }
            }
        }
    }
}