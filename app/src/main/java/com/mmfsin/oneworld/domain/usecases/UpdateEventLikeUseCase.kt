package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IEventsRepository
import javax.inject.Inject

class UpdateEventLikeUseCase @Inject constructor(private val repository: IEventsRepository) {
    suspend operator fun invoke(isLike: Boolean, eventId: String) {
        if (isLike) repository.setEventLike(eventId)
        else repository.removeEventLike(eventId)
    }
}