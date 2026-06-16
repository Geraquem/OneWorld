package com.mmfsin.oneworld.presentation.editprofile

data class EditProfileStates(
    val isLoading: Boolean = false,

    val name: String = "",
    val biography: String? = null,
    val imageUrl: String? = null,
    val website: String? = null,

    val showCloseSessionDialog: Boolean = false,

    val flowCompleted: Boolean = false
)