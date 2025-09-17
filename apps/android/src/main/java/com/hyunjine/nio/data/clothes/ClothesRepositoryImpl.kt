package com.hyunjine.nio.data.clothes

import com.hyunjine.nio.clothes.ClothesRepository
import com.hyunjine.nio.clothes.model.ClothesItemModel
import com.hyunjine.nio.data.clothes.entity.ClothesItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor(
    private val clothesLocalDataSource: ClothesLocalDataSource
): ClothesRepository {
    override fun getClothes(): Flow<List<ClothesItemModel>> {
        return clothesLocalDataSource.getClothes().map { entities ->
            entities.map { entity ->
                ClothesItemModel(link = entity.link, thumbnail = entity.thumbnail, description = entity.description)
            }
        }
    }

    override suspend fun addClothes(vararg clothes: ClothesItemModel) {
        val entity = clothes.map { model ->
            ClothesItemEntity(link = model.link, thumbnail = model.thumbnail, description = model.description)
        }
        clothesLocalDataSource.addClothes(*entity.toTypedArray())
    }
}