package com.mmfsin.oneworld.presentation.profile

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.mmfsin.oneworld.domain.usecases.CheckIfLoggedUseCase
import com.mmfsin.oneworld.domain.usecases.GetOrCreateProfileUseCase
import com.mmfsin.oneworld.domain.usecases.GetMyEventsCreatedUseCase
import com.mmfsin.oneworld.domain.usecases.GetMyProfileUseCase
import com.mmfsin.oneworld.domain.usecases.SignInWithGoogleUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val checkIfLoggedUseCase: CheckIfLoggedUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getOrCreateProfileUseCase: GetOrCreateProfileUseCase,
    private val getMyProfileUseCase: GetMyProfileUseCase,
    private val getMyEventsCreatedUseCase: GetMyEventsCreatedUseCase
) : BaseViewModel<MyProfileStates>(MyProfileStates()) {

    init {
        observeUserProfile()
        checkIfLogged()
    }

    private fun observeUserProfile() {
        getMyProfileUseCase().onEach { profile ->
            if (profile != null) {
                _uiState.update {
                    it.copy(
                        myProfile = profile,
                        userLogged = true
                    )
                }
                getMyEventsCreated(profile.id)
            } else {
                _uiState.update {
                    it.copy(
                        myProfile = null,
                        userLogged = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun sww() = _uiState.update { it.copy(sww = true) }
    fun loading(value: Boolean) = _uiState.update { it.copy(isLoading = value) }

    fun checkIfLogged() {
        executeUseCase(
            { checkIfLoggedUseCase() },
            { logged ->
                if (logged) _uiState.update { it.copy(userLogged = true) }
                else _uiState.update { it.copy(userLogged = false) }
                loading(false)
            },
            {}
        )
    }

    fun signInWithGoogle(): Intent = signInWithGoogleUseCase()

    fun doLogin(result: ActivityResult) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account.email?.let { email ->
                getOrCreateProfile(account.displayName ?: "?", email)
            } ?: run { sww() }
        } catch (e: Exception) {
            sww()
            println("Login ERROR: ${e.message}")
        }
    }

    fun getOrCreateProfile(name: String, email: String) {
        executeUseCase(
            { getOrCreateProfileUseCase(name, email) },
            { /** Flow do his work */ },
            { sww() }
        )
    }

    private fun getMyEventsCreated(userId: String) {
        executeUseCase(
            { getMyEventsCreatedUseCase(userId) },
            { events ->
                events?.let {
                    _uiState.update { it.copy(eventsCreated = events) }
                } ?: run { sww() }
            },
            { sww() })
    }
}