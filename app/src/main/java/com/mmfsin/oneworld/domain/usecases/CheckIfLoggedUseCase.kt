package com.mmfsin.oneworld.domain.usecases

import com.mmfsin.oneworld.domain.interfaces.IUsersRepository
import javax.inject.Inject

class CheckIfLoggedUseCase @Inject constructor(private val repository: IUsersRepository) {
    operator fun invoke(): Boolean = repository.checkIfLogged()
}