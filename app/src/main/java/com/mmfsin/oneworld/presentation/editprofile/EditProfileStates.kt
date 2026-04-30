package com.mmfsin.oneworld.presentation.editprofile

data class EditProfileStates(
    val isLoading: Boolean = true,

    val name: String = "",
    val biography: String = "",
    val imageUrl: String = "",
    val website: String = "",

    val flowCompleted: Boolean = false
)