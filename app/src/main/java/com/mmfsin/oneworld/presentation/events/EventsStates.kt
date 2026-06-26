package com.mmfsin.oneworld.presentation.events

import com.mmfsin.oneworld.domain.models.Event

data class EventsStates(
    val events: List<Event> = emptyList(),
    val isLoading: Boolean = false
)