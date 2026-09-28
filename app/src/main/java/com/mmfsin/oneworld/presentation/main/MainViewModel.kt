package com.mmfsin.oneworld.presentation.main

import androidx.lifecycle.viewModelScope
import com.mmfsin.oneworld.domain.usecases.GetMyProfileUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMyProfileUseCase: GetMyProfileUseCase
) : BaseViewModel<MainStates>(MainStates()) {

    init {
        checkUserSession()
    }

    private fun checkUserSession() {
        viewModelScope.launch {
            getMyProfileUseCase().collect { profile ->
                profile?.let { _uiState.update { it.copy(userSession = profile) } }
            }
        }
    }
}