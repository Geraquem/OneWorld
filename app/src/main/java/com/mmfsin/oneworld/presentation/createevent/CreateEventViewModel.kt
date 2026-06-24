package com.mmfsin.oneworld.presentation.createevent

import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.usecases.CreateEventUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val createEventUseCase: CreateEventUseCase
) : BaseViewModel<CreateEventStates>(CreateEventStates()) {

    fun onTitleChange(title: String) = _uiState.update { it.copy(title = title) }
    fun onDescriptionChange(description: String) = _uiState.update { it.copy(description = description) }
    fun onWebUrlChange(url: String) = _uiState.update { it.copy(webUrl = url) }

    fun timePickerVisibility(visible: Boolean) = _uiState.update { it.copy(showTimePicker = visible) }
    fun datePickerVisibility(visible: Boolean) = _uiState.update { it.copy(showDatePicker = visible) }
    fun categoryDialogVisibility(visible: Boolean) = _uiState.update { it.copy(showCategoryDialog = visible) }

    fun setEventTime(time: Pair<Int, Int>) {
        _uiState.update { it.copy(time = time) }
        timePickerVisibility(visible = false)
    }

    fun setEventDate(date: Long) {
        _uiState.update { it.copy(date = date) }
        datePickerVisibility(visible = false)
    }

    fun updateCategory(categoryId: Int) {
        _uiState.update { it.copy(categoryId = categoryId) }
        categoryDialogVisibility(false)
    }

    fun createEvent() {
        val state = uiState.value
        if (state.title.isNotBlank()
            && state.date != null
            && state.time != null
        ) {
            _uiState.update { it.copy(isLoading = true) }

            val event = Event(
                id = UUID.randomUUID().toString(),
                category = state.categoryId,
                image = "",
                title = state.title,
                description = state.description,
                creatorId = "",
                creatorName = "",
                date = state.date,
                hour = state.time.first,
                minutes = state.time.second,
                address = "",
                webUrl = state.webUrl.ifBlank { null }
            )

            executeUseCase(
                { createEventUseCase(event) },
                { _uiState.update { it.copy(closeAndGoBack = true) } },
                {}
            )

        } else _uiState.update { it.copy(missingFields = true) }
    }
}