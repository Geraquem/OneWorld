package com.mmfsin.oneworld.presentation.editprofile

import androidx.lifecycle.viewModelScope
import com.mmfsin.oneworld.domain.models.UpdateProfileData
import com.mmfsin.oneworld.domain.usecases.CloseSessionUseCase
import com.mmfsin.oneworld.domain.usecases.EditMyProfileUseCase
import com.mmfsin.oneworld.domain.usecases.GetMyProfileUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getMyProfileUseCase: GetMyProfileUseCase,
    private val editMyProfileUseCase: EditMyProfileUseCase,
    private val closeSessionUseCase: CloseSessionUseCase,
) : BaseViewModel<EditProfileStates>(EditProfileStates()) {

    init {
        observeUserProfile()
    }

    private fun observeUserProfile() {
        getMyProfileUseCase().onEach { profile ->
            if (profile != null) {
                _uiState.update {
                    it.copy(
                        name = profile.name,
                        biography = profile.biography,
                        imageUrl = profile.imageUrl,
                        website = profile.website,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun changeName(name: String) = _uiState.update { it.copy(name = name) }
    fun changeImage(uri: String) = _uiState.update { it.copy(imageUrl = uri) }
    fun changeBio(bio: String) = _uiState.update { it.copy(biography = bio) }
    fun changeWebsite(website: String) = _uiState.update { it.copy(website = website) }

    fun saveProfileChanges() {
        _uiState.update { it.copy(isLoading = true) }

        val data = UpdateProfileData(
            imageUrl = uiState.value.imageUrl.checkIfEmpty(),
            name = uiState.value.name,
            biography = uiState.value.biography.checkIfEmpty(),
            website = uiState.value.website.checkIfEmpty(),
        )

        executeUseCase(
            { editMyProfileUseCase(data) },
            { _uiState.update { it.copy(flowCompleted = true) } },
            {}
        )
    }

    fun showCloseSessionDialog(value: Boolean) = _uiState.update { it.copy(showCloseSessionDialog = value) }

    fun closeSession() {
        executeUseCase(
            { closeSessionUseCase() },
            { _uiState.update { it.copy(flowCompleted = true) } },
            {},
        )
    }

    private fun String?.checkIfEmpty(): String? = this?.ifBlank { null }
}