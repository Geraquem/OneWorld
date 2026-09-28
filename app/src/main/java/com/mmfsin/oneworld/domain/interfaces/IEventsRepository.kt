package com.mmfsin.oneworld.domain.interfaces

import com.mmfsin.oneworld.domain.models.Event
import kotlinx.coroutines.flow.Flow

interface IEventsRepository {
    fun getLatestCategory(): Int
    fun updateLatestCategory(newCategory: Int)

    suspend fun getEvents(category: Int): List<Event>?
    suspend fun getEventById(eventId: String): Event?
    suspend fun createEvent(event: Event)

    suspend fun getMyEventsCreated(userId: String): Flow<List<Event>>

    suspend fun setEventLike(eventId: String)
    suspend fun removeEventLike(eventId: String)
    suspend fun saveEvent(eventId: String)
    suspend fun removeSaveEvent(eventId: String)
    suspend fun attendingEvent(eventId: String)
    suspend fun removeAttendingEvent(eventId: String)
}