package com.mmfsin.oneworld.domain.models

data class Event(
    var id: String,
    var category: Int,
    var image: String,
    var title: String,
    var description: String?,
    var webUrl: String?,
    var creatorId: String,
    var creatorName: String,
    var date: Long,
    var hour: Int,
    var minutes: Int,
    var address: String,
)

fun getExampleEvent() = Event(
    id = "",
    category = 2,
    image = "",
    title = "Titulo de Evento",
    description = "Descripción de evento ",
    creatorId = "",
    creatorName = "Mufasa Mufasín",
    date = 32413541,
    hour = 12,
    minutes = 34,
    address = "c/Antonio Lopez 67, Madrid",
    webUrl = "estereotipia.com"
)
