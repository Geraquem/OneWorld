package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IUsersRepository
import com.mmfsin.oneworld.domain.models.UserProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyProfileUseCase @Inject constructor(private val repository: IUsersRepository) {
    operator fun invoke(): Flow<UserProfile?> = repository.getMyProfile()
}