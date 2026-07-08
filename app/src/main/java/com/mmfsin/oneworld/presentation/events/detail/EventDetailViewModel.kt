package com.mmfsin.oneworld.presentation.events.detail

import androidx.lifecycle.SavedStateHandle
import com.mmfsin.oneworld.domain.usecases.GetEventByIdUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventByIdUseCase: GetEventByIdUseCase,
) : BaseViewModel<EventDetailStates>(EventDetailStates()) {

    private val eventId: String? = savedStateHandle["eventId"]

    init {
        getEventById(eventId)
    }

    fun getEventById(eventId: String?) {
        if (eventId == null) sww(true)
        else {
            _uiState.update { it.copy(eventId = eventId) }
            executeUseCase(
                { getEventByIdUseCase(eventId) },
                { event -> _uiState.update { it.copy(event = event) } },
                { sww(true) },
            )
        }
    }

    fun sww(value: Boolean) = _uiState.update { it.copy(sww = value) }
}