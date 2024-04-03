package com.example.project.room.module

import com.example.project.room.User
import com.example.project.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: UserDatabase) =
        appDatabase.userDao()
}