package com.mmfsin.oneworld.domain.usecases

import android.content.Intent
import com.mmfsin.oneworld.domain.interfaces.IUsersRepository
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(private val repository: IUsersRepository) {
    operator fun invoke(): Intent = repository.signInWithGoogle()
}