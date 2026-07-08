package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IEventsRepository
import javax.inject.Inject

class UpdateLatestCategoryEventsUseCase @Inject constructor(private val repository: IEventsRepository) {
    operator fun invoke(newCategory: Int) = repository.updateLatestCategory(newCategory)
}