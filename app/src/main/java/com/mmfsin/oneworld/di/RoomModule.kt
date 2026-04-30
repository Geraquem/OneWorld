package com.mmfsin.oneworld.di

import android.content.Context
import androidx.room.Room
import com.mmfsin.oneworld.data.ddbb.RoomConfiguration
import com.mmfsin.oneworld.data.ddbb.daos.EventsDAO
import com.mmfsin.oneworld.data.ddbb.daos.UsersDAO
import com.mmfsin.oneworld.utils.DDBB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RoomConfiguration =
        Room.databaseBuilder(
            context,
            RoomConfiguration::class.java,
            DDBB_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    fun provideUsersDao(db: RoomConfiguration): UsersDAO = db.usersDAO()

    @Provides
    fun provideEventsDao(db: RoomConfiguration): EventsDAO = db.eventsDAO()
}