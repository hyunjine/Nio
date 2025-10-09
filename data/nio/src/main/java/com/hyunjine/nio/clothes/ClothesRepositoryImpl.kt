package com.hyunjine.nio.clothes

import com.hyunjine.clothes.ClothesRepository
import com.hyunjine.clothes.model.ClothesItemModel
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor(
    private val clothesRemoteDataSource: ClothesRemoteDataSource
): ClothesRepository {
    override suspend fun getClothes(): List<ClothesItemModel> {
        return clothesRemoteDataSource.getClothes().map { entity ->
            ClothesItemModel(
                id = entity.id,
                link = entity.link,
                thumbnail = entity.thumbnail,
                description = entity.description
            )
        }
    }

    override suspend fun addClothes(
        link: String,
        thumbnail: String?,
        description: String
    ) {
        clothesRemoteDataSource.addClothes(link = link, thumbnail = thumbnail, description = description)
    }

    override suspend fun removeClothes(id: Long) {
        clothesRemoteDataSource.removeClothes(id)
    }
}