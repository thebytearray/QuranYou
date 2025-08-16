package org.thebytearray.quran.android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.thebytearray.quran.android.data.repository.UserPreferencesRepositoryImpl
import org.thebytearray.quran.android.domain.repository.UserPreferencesRepository


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun bindUserRepository(impl: UserPreferencesRepositoryImpl): UserPreferencesRepository


}