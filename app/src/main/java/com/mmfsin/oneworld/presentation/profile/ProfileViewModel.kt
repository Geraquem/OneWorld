package com.mmfsin.oneworld.presentation.profile

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.mmfsin.oneworld.domain.usecases.CheckIfUserLoggedUseCase
import com.mmfsin.oneworld.domain.usecases.GetOrCreateUserUseCase
import com.mmfsin.oneworld.domain.usecases.GetUserEventsUseCase
import com.mmfsin.oneworld.domain.usecases.GetUserProfileUseCase
import com.mmfsin.oneworld.domain.usecases.SignInWithGoogleUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val checkIfUserLoggedUseCase: CheckIfUserLoggedUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getOrCreateUserUseCase: GetOrCreateUserUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserEventsUseCase: GetUserEventsUseCase
) : BaseViewModel<ProfileStates>(ProfileStates()) {

    init {
        observeUserProfile()
        checkIfUserLogged()
    }

    private fun observeUserProfile() {
        getUserProfileUseCase().onEach { profile ->
            if (profile != null) {
                _uiState.update {
                    it.copy(userProfile = profile)
                }
                getUserEvents(profile.id)
            }
        }.launchIn(viewModelScope)
    }

    fun sww() = _uiState.update { it.copy(sww = true) }
    fun loading(value: Boolean) = _uiState.update { it.copy(isLoading = value) }

    fun checkIfUserLogged() {
        executeUseCase(
            { checkIfUserLoggedUseCase() },
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
                getOrCreateUserSession(account.displayName ?: "?", email)
            } ?: run { sww() }
        } catch (e: Exception) {
            sww()
            println("Login ERROR: ${e.message}")
        }
    }

    fun getOrCreateUserSession(name: String, email: String) {
        executeUseCase(
            { getOrCreateUserUseCase(name, email) },
            { profile ->
                profile?.let { p ->
                    _uiState.update {
                        it.copy(
                            userProfile = p,
                            isLoading = false
                        )
                    }
                } ?: run { sww() }
            },
            { sww() }
        )
    }

    private fun getUserEvents(userId: String) {
        executeUseCase(
            { getUserEventsUseCase(userId) },
            { events ->
                events?.let {
                    _uiState.update { it.copy(eventsCreated = events) }
                } ?: run { sww() }
            },
            { sww() })
    }
}