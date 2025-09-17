package com.hyunjine.nio.data.clothes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hyunjine.nio.data.clothes.entity.ClothesItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClothesLocalDataSource {
    @Query("SELECT * FROM clothes")
    fun getClothes(): Flow<List<ClothesItemEntity>>

//    @Query("SELECT * FROM clothes WHERE id IN (:userIds)")
//    suspend fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User
//
    @Insert
    suspend fun addClothes(vararg users: ClothesItemEntity)

    @Query("DELETE FROM clothes WHERE id = :id")
    suspend fun removeClothes(id: Long)
}