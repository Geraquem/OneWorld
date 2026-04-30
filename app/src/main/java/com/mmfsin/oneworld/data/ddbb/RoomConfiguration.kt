package com.mmfsin.oneworld.data.ddbb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmfsin.oneworld.data.ddbb.daos.EventsDAO
import com.mmfsin.oneworld.data.ddbb.daos.UsersDAO
import com.mmfsin.oneworld.data.models.EventDTO
import com.mmfsin.oneworld.data.models.UserProfileDTO

@Database(entities = [UserProfileDTO::class, EventDTO::class], version = 1)
abstract class RoomConfiguration : RoomDatabase() {
    abstract fun usersDAO(): UsersDAO
    abstract fun eventsDAO(): EventsDAO
}
