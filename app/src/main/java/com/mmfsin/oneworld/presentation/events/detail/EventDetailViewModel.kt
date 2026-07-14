package com.mmfsin.oneworld.presentation.events.detail

import androidx.lifecycle.SavedStateHandle
import com.mmfsin.oneworld.domain.usecases.GetEventByIdUseCase
import com.mmfsin.oneworld.domain.usecases.UpdateAssistEventUseCase
import com.mmfsin.oneworld.domain.usecases.UpdateEventLikeUseCase
import com.mmfsin.oneworld.domain.usecases.UpdateSaveEventUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val updateEventLikeUseCase: UpdateEventLikeUseCase,
    private val updateSaveEventUseCase: UpdateSaveEventUseCase,
    private val updateAssistEventUseCase: UpdateAssistEventUseCase,
) : BaseViewModel<EventDetailStates>(EventDetailStates()) {

    private val eventId: String? = savedStateHandle["eventId"]

    init {
        getEventById(eventId)
    }

    fun getEventById(eventId: String?) {
        if (eventId == null) sww()
        else {
            _uiState.update { it.copy(eventId = eventId) }
            executeUseCase(
                { getEventByIdUseCase(eventId) },
                { event -> _uiState.update { it.copy(event = event) }

                },
                { sww() },
            )
        }
    }

    fun likeEvent() {
        val eventId = uiState.value.eventId
        val currentLike = (uiState.value.event?.userLiked) ?: false
        val event = uiState.value.event
        eventId?.let { id ->
            executeUseCase(
                { updateEventLikeUseCase(!currentLike, id) },
                {
                    _uiState.update {
                        it.copy(
                            event = event?.copy(
                                userLiked = !currentLike,
                                likesCount = if (!currentLike) (event.likesCount) + 1 else (event.likesCount) - 1
                            )
                        )
                    }
                },
                {}
            )
        } ?: run { sww() }
    }

    fun saveEvent() {
        val eventId = uiState.value.eventId
        val currentSaved = (uiState.value.event?.userSaved) ?: false
        val event = uiState.value.event
        eventId?.let { id ->
            executeUseCase(
                { updateSaveEventUseCase(!currentSaved, id) },
                {
                    _uiState.update {
                        it.copy(
                            event = event?.copy(
                                userSaved = !currentSaved,
                                savesCount = if (!currentSaved) (event.savesCount) + 1 else (event.savesCount) - 1
                            )
                        )
                    }
                },
                {}
            )
        } ?: run { sww() }
    }

    fun assistEvent() {
        val eventId = uiState.value.eventId
        val currentAttending = (uiState.value.event?.userAttending) ?: false
        val event = uiState.value.event
        eventId?.let { id ->
            executeUseCase(
                { updateAssistEventUseCase(!currentAttending, id) },
                {
                    _uiState.update {
                        it.copy(
                            event = event?.copy(
                                userAttending = !currentAttending,
                                attendeesCount = if (!currentAttending) (event.attendeesCount) + 1 else (event.attendeesCount) - 1
                            )
                        )
                    }
                },
                {}
            )
        } ?: run { sww() }
    }

    fun sww(show: Boolean = true) = _uiState.update { it.copy(sww = show) }
}