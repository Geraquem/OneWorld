package com.mmfsin.oneworld.presentation.events

import com.mmfsin.oneworld.domain.models.Event

data class EventsStates(
    val showCategoryDialog: Boolean = false,

    val searchingCategory: Int = 0,
    val events: List<Event> = emptyList(),

    val isLoading: Boolean = true
)