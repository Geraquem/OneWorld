package com.mmfsin.oneworld.data.mappers

import com.mmfsin.oneworld.data.models.EventDTO
import com.mmfsin.oneworld.domain.models.Event

fun Event.toEventDTO() = EventDTO(
    id = id,
    category = category,
    image = image,
    title = title,
    description = description,
    webUrl = webUrl,
    creatorId = creatorId,
    creatorName = creatorName,
    date = date,
    hour = hour,
    minutes = minutes,
    address = address
)
