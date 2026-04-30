package com.mmfsin.oneworld.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mmfsin.oneworld.utils.TABLE_EVENTS

@Entity(tableName = TABLE_EVENTS)
data class EventDTO(
    @PrimaryKey
    var id: String = "",
    var type: String = "",
    var image: String = "",
    var title: String = "",
    var description: String = "",
    var creatorId: String = "",
    var creatorName: String = "",
    var time: String = "",
    var address: String = "",
)
