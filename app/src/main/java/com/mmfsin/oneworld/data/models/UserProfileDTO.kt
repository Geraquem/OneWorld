package com.mmfsin.oneworld.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mmfsin.oneworld.utils.TABLE_USERS

@Entity(tableName = TABLE_USERS)
data class UserProfileDTO(
    @PrimaryKey var id: String = "",
    var email: String = "",
    var imageUrl: String = "",
    var name: String = "",
    var biography: String = "",
    var website: String = ""
)