package com.mmfsin.oneworld.presentation.profile

import android.content.Intent
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.UserProfile

data class ProfileStates(
    val signInIntent: Intent? = null,

    val userProfile: UserProfile? = null,
    val eventsCreated: List<Event>? = null,

    val isLoading: Boolean = true,
    val userLogged: Boolean = false,

    val sww: Boolean = false
)
