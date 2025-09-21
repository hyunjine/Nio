package com.hyunjine.nio.data.clothes.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClothesItemEntity(
    val id: Long = 0,
    val link: String = "",
    val thumbnail: String? = null,
    val description: String = ""
)

//@Entity(tableName = "clothes")
//@Serializable
//data class ClothesItemEntity(
//    @PrimaryKey(autoGenerate = true) val id: Long = 0,
//    @ColumnInfo(name = "link") val link: String,
//    @ColumnInfo(name = "thumbnail") val thumbnail: String?,
//    @ColumnInfo(name = "description") val description: String
//)