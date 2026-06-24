package com.mmfsin.oneworld.presentation.createevent

data class CreateEventStates(
    val isLoading: Boolean = false,

    val title: String = "",
    val description: String = "",
    val webUrl: String = "",
    val time: Pair<Int, Int>? = null,
    val date: Long? = null,
    val categoryId: Int = 0,

    val showTimePicker: Boolean = false,
    val showDatePicker: Boolean = false,
    val showCategoryDialog: Boolean = false,

    val closeAndGoBack: Boolean = false
)