package com.mmfsin.oneworld.domain.models

data class UpdateProfileData(
    var imageUrl: String = "",
    var name: String = "",
    var biography: String = "",
    var website: String = ""
)