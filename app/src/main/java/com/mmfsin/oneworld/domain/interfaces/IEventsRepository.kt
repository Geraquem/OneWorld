package com.mmfsin.oneworld.domain.interfaces

import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.EventCategory

interface IEventsRepository {
    fun getLatestCategory():Int
    fun updateLatestCategory(newCategory: Int)

    suspend fun getEvents(): List<Event>?
    suspend fun createEvent(event: Event)

    suspend fun getMyEventsCreated(userId: String): List<Event>?
}