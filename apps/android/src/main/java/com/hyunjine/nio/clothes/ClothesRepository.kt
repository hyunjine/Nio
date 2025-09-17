package com.hyunjine.nio.clothes

import com.hyunjine.nio.clothes.model.ClothesItemModel

interface ClothesRepository {
    suspend fun getClothes(): List<ClothesItemModel>

    suspend fun addClothes(vararg clothes: ClothesItemModel)
}