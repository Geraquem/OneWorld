package com.mmfsin.oneworld.presentation.createevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.usecases.CreateEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val createEventUseCase: CreateEventUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateEventStates())
    val uiState: StateFlow<CreateEventStates> = _uiState

    fun onTitleChange(title: String) = _uiState.update { it.copy(title = title) }
    fun onDescriptionChange(description: String) = _uiState.update { it.copy(description = description) }
    fun onWebUrlChange(url: String) = _uiState.update { it.copy(webUrl = url) }

    fun timePickerVisibility(visible: Boolean) = _uiState.update { it.copy(showTimePicker = visible) }
    fun datePickerVisibility(visible: Boolean) = _uiState.update { it.copy(showDatePicker = visible) }

    fun setEventTime(time: Pair<String, String>) {
        _uiState.update { it.copy(time = time) }
        timePickerVisibility(visible = false)
    }

    fun setEventDate(date: String) {
        _uiState.update { it.copy(date = date) }
        datePickerVisibility(visible = false)
    }

    fun createEvent() {
        _uiState.update { it.copy(isLoading = true) }

        val event = Event(
            id = "",
            type = "TEST",
            image = "",
            title = uiState.value.title,
            description = uiState.value.description,
            creatorId = "",
            creatorName = "",
            time = uiState.value.time.toString(),
            address = "",
            webUrl = uiState.value.webUrl.ifBlank { null }
        )

        viewModelScope.launch(Dispatchers.IO) {
            val result = createEventUseCase(event)
            result.onSuccess { _uiState.update { it.copy(closeAndGoBack = true) } }
            result.onFailure { }
        }
    }
}