package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IEventsRepository
import javax.inject.Inject

class UpdateAssistEventUseCase @Inject constructor(private val repository: IEventsRepository) {
    suspend operator fun invoke(attending: Boolean, eventId: String) {
        if (attending) repository.attendingEvent(eventId)
        else repository.removeAttendingEvent(eventId)
    }
}