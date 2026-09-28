package com.mmfsin.oneworld.presentation.login

data class LoginStates(
    val isLoading: Boolean = true,
    val userLogged: Boolean = false,
    val sww: Boolean = false
)
