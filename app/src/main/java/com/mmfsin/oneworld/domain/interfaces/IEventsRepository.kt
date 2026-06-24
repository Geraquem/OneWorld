package com.mmfsin.oneworld.domain.interfaces

import com.mmfsin.oneworld.domain.models.Event

interface IEventsRepository {
    suspend fun getEvents(): List<Event>?
    suspend fun createEvent(event: Event)

    suspend fun getUserEvents(userId: String): List<Event>?
}