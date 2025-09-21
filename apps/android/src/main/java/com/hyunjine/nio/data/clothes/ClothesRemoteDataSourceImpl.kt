package com.hyunjine.nio.data.clothes

import com.hyunjine.nio.data.clothes.entity.ClothesItemEntity
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ClothesRemoteDataSourceImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
): ClothesRemoteDataSource {
    override suspend fun getClothes(): List<ClothesItemEntity> {
        return supabaseClient.from("clothes")
            .select().decodeList<ClothesItemEntity>()
    }

    override suspend fun addClothes(link: String, thumbnail: String?, description: String) {
        val entity = ClothesItemEntity(
            link = link, thumbnail = thumbnail, description = description
        )
        supabaseClient.from("clothes")
            .insert(entity)
    }

    override suspend fun removeClothes(id: Long) {
        supabaseClient.from("clothes")
            .delete {
                filter {
                    eq("id", id)
                }
            }
    }
}