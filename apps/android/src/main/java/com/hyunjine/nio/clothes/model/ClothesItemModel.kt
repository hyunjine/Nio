package com.hyunjine.nio.clothes.model

/**
 * @property id room sdk에서 자동으로 만들어 집니다.
 */
data class ClothesItemModel(
    val id: Long = 0,
    val link: String,
    val thumbnail: String?,
    val description: String
)
