package com.mmfsin.oneworld.presentation.events.detail

import com.mmfsin.oneworld.domain.models.Event

data class EventDetailStates(
    val eventId: String? = null,
    val event: Event? = null,

    val sww: Boolean = false
)