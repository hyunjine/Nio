package com.hyunjine.nio.module.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {
    @Provides
    @Singleton
    fun provideSuperbaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://ctkpujorpenkjailzvzy.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImN0a3B1am9ycGVua2phaWx6dnp5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTg0Mjc3NjgsImV4cCI6MjA3NDAwMzc2OH0.ZF7fe6CbxR3F9vKPf52C9lr6LJP5mcP6RBVJ3u1Bs5A"
        ) {
            install(Postgrest)
        }
    }
}