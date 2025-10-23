package com.hyunjine.clothes.list.model

import kotlinx.serialization.Serializable

/**
 * @property id room sdk에서 자동으로 만들어 집니다.
 */
@Serializable
data class ClothesItemModel(
    val id: Long,
    val link: String,
    val thumbnail: String?,
    val description: String
)
