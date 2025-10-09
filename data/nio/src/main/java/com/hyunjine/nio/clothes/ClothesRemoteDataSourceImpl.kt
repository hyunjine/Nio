package com.hyunjine.nio.clothes

import com.hyunjine.nio.clothes.entity.ClothesItemEntity
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

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