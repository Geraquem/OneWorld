package com.mmfsin.oneworld.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mmfsin.oneworld.utils.TABLE_EVENTS

@Entity(tableName = TABLE_EVENTS)
data class EventDTO(
    @PrimaryKey
    var id: String = "",
    var category: Int = 0,
    var image: String = "",
    var title: String = "",
    var description: String? = null,
    var webUrl: String? = null,
    var creatorId: String = "",
    var creatorName: String = "",
    var date: Long = 0,
    var hour: Int = 0,
    var minutes: Int = 0,
    var address: String = "",
)
