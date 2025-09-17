package com.hyunjine.nio.module.repo

import com.hyunjine.nio.clothes.ClothesRepository
import com.hyunjine.nio.data.clothes.ClothesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindClothesRepository(impl: ClothesRepositoryImpl): ClothesRepository
}