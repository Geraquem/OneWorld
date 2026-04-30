package com.mmfsin.oneworld.domain.models

data class UserProfile(
    var id: String = "",
    var email: String = "",
    var name: String = "",
    var imageUrl: String = "",
    var biography: String = "",
    var website: String = ""
)