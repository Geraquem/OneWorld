package com.mmfsin.oneworld.presentation.editprofile

import com.mmfsin.oneworld.domain.models.UpdateProfileData
import com.mmfsin.oneworld.domain.usecases.EditUserProfileUseCase
import com.mmfsin.oneworld.domain.usecases.GetUserProfileUseCase
import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val editUserProfileUseCase: EditUserProfileUseCase,
) : BaseViewModel<EditProfileStates>(EditProfileStates()) {

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        executeUseCase(
            { getUserProfileUseCase() },
            { profile ->
                profile?.let {
                    _uiState.update {
                        it.copy(
                            name = profile.name,
                            biography = profile.biography,
                            imageUrl = profile.imageUrl,
                            website = profile.website
                        )
                    }
                }
            },
            {}
        )
    }

    fun changeName(name: String) = _uiState.update { it.copy(name = name) }
    fun changeBio(bio: String) = _uiState.update { it.copy(biography = bio) }
    fun changeWebsite(website: String) = _uiState.update { it.copy(website = website) }

    fun saveProfileChanges() {

        _uiState.update { it.copy(isLoading = true) }

        val data = UpdateProfileData(
            imageUrl = uiState.value.imageUrl,
            name = uiState.value.name,
            biography = uiState.value.biography,
            website = uiState.value.website,
        )

        executeUseCase(
            { editUserProfileUseCase(data) },
            {
                _uiState.update {
                    it.copy(
                        flowCompleted = true,
                        isLoading = false
                    )
                }
            },
            {}
        )
    }
}