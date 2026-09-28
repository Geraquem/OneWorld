package com.mmfsin.oneworld.presentation.login

import android.content.Intent
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.mmfsin.oneworld.domain.usecases.GetMyEventsCreatedUseCase
import com.mmfsin.oneworld.domain.usecases.GetMyProfileUseCase
import com.mmfsin.oneworld.domain.usecases.GetOrCreateProfileUseCase
import com.mmfsin.oneworld.domain.usecases.SignInWithGoogleUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getOrCreateProfileUseCase: GetOrCreateProfileUseCase,
    private val getMyProfileUseCase: GetMyProfileUseCase,
    private val getMyEventsCreatedUseCase: GetMyEventsCreatedUseCase
) : BaseViewModel<LoginStates>(LoginStates()) {

    fun signInWithGoogle(): Intent = signInWithGoogleUseCase()

    fun doLogin(result: ActivityResult) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account.email?.let { email ->
                getOrCreateProfile(account.displayName, email)
            } ?: run { sww() }
        } catch (e: Exception) {
            println("Login ERROR: ${e.message}")
            sww()
        }
    }

    fun getOrCreateProfile(name: String?, email: String) {
        executeUseCase(
            { getOrCreateProfileUseCase(name, email) },
            { _uiState.update { it.copy(userLogged = true) } },
            { sww() }
        )
    }

    fun sww() = _uiState.update { it.copy(sww = true) }
}