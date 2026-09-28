package com.mmfsin.oneworld.presentation.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object UserProfile

@Serializable
object EditProfile

@Serializable
object CreateEvent

@Serializable
data class EventDetail(val eventId: String?)




@Serializable
object AAAScreen