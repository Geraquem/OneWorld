package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IUsersRepository
import javax.inject.Inject

class GetOrCreateProfileUseCase @Inject constructor(private val repository: IUsersRepository) {

    suspend operator fun invoke(name: String, email: String) = repository.getOrCreateProfile(name, email)
}