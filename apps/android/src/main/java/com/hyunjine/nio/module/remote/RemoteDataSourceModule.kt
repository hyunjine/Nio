package com.hyunjine.nio.module.remote

import com.hyunjine.nio.data.clothes.ClothesRemoteDataSource
import com.hyunjine.nio.data.clothes.ClothesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.ktor.client.HttpClient
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