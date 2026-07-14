package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IEventsRepository
import javax.inject.Inject

class UpdateSaveEventUseCase @Inject constructor(private val repository: IEventsRepository) {
    suspend operator fun invoke(saveEvent: Boolean, eventId: String) {
        if (saveEvent) repository.saveEvent(eventId)
        else repository.removeSaveEvent(eventId)
    }
}