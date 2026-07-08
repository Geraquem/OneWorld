package com.mmfsin.oneworld.presentation.profile.userprofile

import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.UserProfile

data class UserProfileStates(
    val profile: UserProfile? = null,
    val eventsCreated: List<Event>? = null,

    val isLoading: Boolean = true,
    val sww: Boolean = false
)
