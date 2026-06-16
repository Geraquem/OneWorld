package com.mmfsin.oneworld.domain.models

data class UserProfile(
    var id: String = "",
    var email: String = "",
    var name: String = "",
    var imageUrl: String? = null,
    var biography: String? = null,
    var website: String? = null
)