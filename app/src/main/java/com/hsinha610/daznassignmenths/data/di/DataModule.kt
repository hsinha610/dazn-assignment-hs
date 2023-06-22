package com.hsinha610.daznassignmenths.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hsinha610.daznassignmenths.data.repo.Repository
import com.hsinha610.daznassignmenths.data.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    @ViewModelScoped
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @ViewModelScoped
    fun providesRepository(repositoryImpl: RepositoryImpl): Repository = repositoryImpl

}