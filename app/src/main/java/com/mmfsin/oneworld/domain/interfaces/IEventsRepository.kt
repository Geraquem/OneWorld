package com.mmfsin.oneworld.domain.interfaces

import com.mmfsin.oneworld.domain.models.Event

interface IEventsRepository {
    fun getLatestCategory(): Int
    fun updateLatestCategory(newCategory: Int)

    suspend fun getEvents(category: Int): List<Event>?
    suspend fun createEvent(event: Event)

    suspend fun getMyEventsCreated(userId: String): List<Event>?
}