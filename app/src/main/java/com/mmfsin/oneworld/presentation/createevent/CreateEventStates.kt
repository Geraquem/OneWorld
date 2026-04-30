package com.mmfsin.oneworld.presentation.createevent

data class CreateEventStates(
    val isLoading: Boolean = false,

    val title: String = "",
    val description: String = "",

    val showTimePicker: Boolean = false,
    val time: Pair<String, String>? = null,

    val closeAndGoBack: Boolean = false
)