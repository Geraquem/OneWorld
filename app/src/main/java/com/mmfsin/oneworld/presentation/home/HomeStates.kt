package com.mmfsin.oneworld.presentation.home

import com.mmfsin.oneworld.domain.models.Event

data class HomeStates(
    val events: List<Event> = emptyList(),
    val isLoading: Boolean = false
)