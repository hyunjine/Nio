package com.hyunjine.nio.clothes

import com.hyunjine.nio.clothes.entity.ClothesItemEntity

interface ClothesRemoteDataSource {
    suspend fun getClothes(): List<ClothesItemEntity>

    suspend fun addClothes(
        link: String,
        thumbnail: String? = null,
        description: String
    )

    suspend fun removeClothes(id: Long)
}