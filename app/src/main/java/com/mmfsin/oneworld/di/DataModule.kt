package com.mmfsin.oneworld.di

import com.mmfsin.oneworld.data.repository.EventsRepository
import com.mmfsin.oneworld.data.repository.UsersRepository
import com.mmfsin.oneworld.domain.interfaces.IEventsRepository
import com.mmfsin.oneworld.domain.interfaces.IUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindEventsRepository(repository: EventsRepository): IEventsRepository

    @Binds
    fun bindUsersRepository(repository: UsersRepository): IUsersRepository
}