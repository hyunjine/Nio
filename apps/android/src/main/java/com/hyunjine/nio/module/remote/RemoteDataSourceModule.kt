package com.hyunjine.nio.module.remote

import com.hyunjine.nio.clothes.ClothesRemoteDataSource
import com.hyunjine.nio.clothes.ClothesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideClothesRemoteDataSource(supabaseClient: SupabaseClient): ClothesRemoteDataSource {
        return ClothesRemoteDataSourceImpl(supabaseClient = supabaseClient)
    }
}