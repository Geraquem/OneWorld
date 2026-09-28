package com.mmfsin.oneworld.presentation.profile.myprofile

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.mmfsin.oneworld.domain.usecases.GetMyEventsCreatedUseCase
import com.mmfsin.oneworld.domain.usecases.GetMyProfileUseCase
import com.mmfsin.oneworld.domain.usecases.GetOrCreateProfileUseCase
import com.mmfsin.oneworld.domain.usecases.SignInWithGoogleUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getOrCreateProfileUseCase: GetOrCreateProfileUseCase,
    private val getMyProfileUseCase: GetMyProfileUseCase,
    private val getMyEventsCreatedUseCase: GetMyEventsCreatedUseCase
) : BaseViewModel<MyProfileStates>(MyProfileStates()) {

    init {
        checkUserProfile()
    }

    private fun checkUserProfile() {
        viewModelScope.launch {
            getMyProfileUseCase().collect { profile ->
                _uiState.update {
                    it.copy(
                        userProfile = profile,
                        isLoading = false
                    )
                }
                if (profile != null) getMyEventsCreated(profile.id)
            }
        }
    }

    fun signInWithGoogle(): Intent = signInWithGoogleUseCase()

    fun doLogin(result: ActivityResult) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account.email?.let { email ->
                getOrCreateProfile(account.displayName, email)
            } ?: run { sww() }
        } catch (e: Exception) {
            sww()
            println("Login ERROR: ${e.message}")
        }
    }

    fun getOrCreateProfile(name: String?, email: String) {
        executeUseCase(
            { getOrCreateProfileUseCase(name, email) },
            { /** Flow do his work */ },
            { sww() }
        )
    }

    private fun getMyEventsCreated(userId: String) {
        viewModelScope.launch {
            getMyEventsCreatedUseCase(userId).collect { events ->
                _uiState.update { it.copy(eventsCreated = events) }
            }
        }
    }

    fun sww() = _uiState.update { it.copy(sww = true) }
}