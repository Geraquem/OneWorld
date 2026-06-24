package com.mmfsin.oneworld.data.mappers

import com.mmfsin.oneworld.data.models.EventDTO
import com.mmfsin.oneworld.data.models.UserProfileDTO
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.UserProfile

fun EventDTO.toEvent() = Event(
    id = id,
    type = type,
    title = title,
    description = description,
    webUrl = webUrl,
    image = image,
    creatorId = creatorId,
    creatorName = creatorName,
    address = address,
    date = date,
    hour = hour,
    minutes = minutes
)

fun List<EventDTO>.toEventList() = this.map { it.toEvent() }

fun UserProfileDTO.toUserProfile() = UserProfile(
    id = id,
    email = email,
    name = name,
    imageUrl = imageUrl,
    biography = biography,
    website = website
)