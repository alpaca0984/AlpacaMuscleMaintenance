package com.example.alpacamusclemaintenance.di

import com.example.alpacamusclemaintenance.db.repository.PushUpRepositoryImpl
import com.example.alpacamusclemaintenance.domain.pushup.PushUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindPushUpRepository(
        pushUpRepositoryImpl: PushUpRepositoryImpl
    ): PushUpRepository
}
