package com.mmfsin.oneworld.domain.models

data class UpdateProfileData(
    var name: String,
    var imageUrl: String? = null,
    var biography: String? = null,
    var website: String? = null
)