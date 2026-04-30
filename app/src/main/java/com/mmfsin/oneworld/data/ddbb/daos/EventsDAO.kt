package com.mmfsin.oneworld.data.ddbb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mmfsin.oneworld.data.models.EventDTO
import com.mmfsin.oneworld.data.models.UserProfileDTO

@Dao
interface EventsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserEvents(events: List<EventDTO>)

    @Query("SELECT * FROM table_events WHERE creatorId = :userId")
    suspend fun getUserEvents(userId: String): List<EventDTO>
}