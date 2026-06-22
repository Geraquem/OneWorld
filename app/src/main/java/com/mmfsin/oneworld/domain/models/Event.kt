package com.mmfsin.oneworld.domain.models

data class Event(
    var id: String,
    var type: String,
    var image: String,
    var title: String,
    var description: String,
    var creatorId: String,
    var creatorName: String,
    var time: String,
    var address: String,
    var webUrl: String?,
)

fun getExampleEvent() = Event(
    id = "",
    type = "",
    image = "",
    title = "Titulo de Evento",
    description = "Descripción de evento porque esto puede ser muy largo yuhu vamos allá",
    creatorId = "",
    creatorName = "",
    time = "",
    address = "",
    webUrl = ""
)
