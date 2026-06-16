package com.mmfsin.oneworld.presentation.editprofile

data class EditProfileStates(
    val isLoading: Boolean = true,

    val name: String = "",
    val biography: String? = null,
    val imageUrl: String? = null,
    val website: String? = null,

    val flowCompleted: Boolean = false
)