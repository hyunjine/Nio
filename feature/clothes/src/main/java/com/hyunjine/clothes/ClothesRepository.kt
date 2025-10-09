package com.hyunjine.clothes

import com.hyunjine.clothes.model.ClothesItemModel

interface ClothesRepository {
    suspend fun getClothes(): List<ClothesItemModel>

    suspend fun addClothes(
        link: String,
        thumbnail: String? = null,
        description: String
    )

    suspend fun removeClothes(id: Long)

    suspend fun fetchThumbnail(url: String): String?
}