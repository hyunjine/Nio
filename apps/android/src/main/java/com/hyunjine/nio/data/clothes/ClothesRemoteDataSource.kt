package com.hyunjine.nio.data.clothes

import com.hyunjine.nio.clothes.model.ClothesItemModel
import com.hyunjine.nio.data.clothes.entity.ClothesItemEntity

interface ClothesRemoteDataSource {
    suspend fun getClothes(): List<ClothesItemEntity>

    suspend fun addClothes(
        link: String,
        thumbnail: String? = null,
        description: String
    )

    suspend fun removeClothes(id: Long)
}