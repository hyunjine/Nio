package com.hyunjine.nio.clothes

import com.hyunjine.nio.clothes.model.ClothesItemModel
import kotlinx.coroutines.flow.Flow

interface ClothesRepository {
    fun getClothes(): Flow<List<ClothesItemModel>>

    suspend fun addClothes(vararg clothes: ClothesItemModel)
}