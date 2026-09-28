package com.mmfsin.oneworld.presentation.profile.myprofile

import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.UserProfile

data class MyProfileStates(
    val isLoading: Boolean = true,

    val userProfile: UserProfile? = null,
    val eventsCreated: List<Event>? = null,

    val sww: Boolean = false
)
