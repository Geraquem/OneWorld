package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IUsersRepository
import com.mmfsin.oneworld.domain.models.UserProfile
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(private val repository: IUsersRepository) {
    suspend operator fun invoke(): UserProfile? = repository.getUserProfile()
}