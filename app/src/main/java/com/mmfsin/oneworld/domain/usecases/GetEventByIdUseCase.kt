package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IEventsRepository
import com.mmfsin.oneworld.domain.models.Event
import javax.inject.Inject

class GetEventByIdUseCase @Inject constructor(private val repository: IEventsRepository) {
    suspend operator fun invoke(eventId: String): Event? = repository.getEventById(eventId)
}