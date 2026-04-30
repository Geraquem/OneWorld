package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IUsersRepository
import com.mmfsin.oneworld.domain.models.UpdateProfileData
import javax.inject.Inject

class EditUserProfileUseCase @Inject constructor(private val repository: IUsersRepository) {
    suspend operator fun invoke(data: UpdateProfileData) = repository.editUserProfile(data)
}