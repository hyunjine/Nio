package com.hyunjine.nio.data.clothes

import androidx.room.Dao
import androidx.room.Query
import com.hyunjine.nio.data.clothes.entity.ClothesItemEntity

@Dao
interface ClothesLocalDataSource {
    @Query("SELECT * FROM clothes")
    suspend fun getClothes(): List<ClothesItemEntity>

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User
//
//    @Insert
//    fun insertAll(vararg users: User)
//
//    @Delete
//    fun delete(user: User)
}