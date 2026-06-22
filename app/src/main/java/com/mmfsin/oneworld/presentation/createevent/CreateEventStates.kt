package com.mmfsin.oneworld.presentation.createevent

data class CreateEventStates(
    val isLoading: Boolean = false,

    val title: String = "",
    val description: String = "",
    val time: Pair<String, String>? = null,
    val date: String? = null,

    val showTimePicker: Boolean = false,

    val closeAndGoBack: Boolean = false
)