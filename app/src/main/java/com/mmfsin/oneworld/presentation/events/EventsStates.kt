package com.mmfsin.oneworld.presentation.events

import com.mmfsin.oneworld.domain.models.Event

data class EventsStates(
    val searchingCategory: Int = 0,
    val events: List<Event> = emptyList(),

    val categoryDialogVisibility: Boolean = false,

    val isLoading: Boolean = true
)