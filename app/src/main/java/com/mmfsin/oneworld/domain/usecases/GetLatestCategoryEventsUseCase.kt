package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IEventsRepository
import com.mmfsin.oneworld.domain.models.Event
import javax.inject.Inject

class GetLatestCategoryEventsUseCase @Inject constructor(private val repository: IEventsRepository) {
    operator fun invoke(): Int = repository.getLatestCategory()
}