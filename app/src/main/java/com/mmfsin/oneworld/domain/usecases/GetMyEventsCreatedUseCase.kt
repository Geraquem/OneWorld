package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IEventsRepository
import com.mmfsin.oneworld.domain.models.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyEventsCreatedUseCase @Inject constructor(private val repository: IEventsRepository) {
    suspend operator fun invoke(userId: String): Flow<List<Event>> =
        repository.getMyEventsCreated(userId)
}