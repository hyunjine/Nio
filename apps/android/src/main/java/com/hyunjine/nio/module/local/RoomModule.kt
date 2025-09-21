package com.hyunjine.nio.module.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hyunjine.nio.data.clothes.ClothesLocalDataSource
import com.hyunjine.nio.data.clothes.entity.ClothesItemEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
//    @Provides
//    fun provideDatabase(
//        @ApplicationContext context: Context
//    ): NioDatabase {
//         return Room.databaseBuilder(
//             context,
//            NioDatabase::class.java, "nio-database"
//        ).build()
//    }
//
//    @Provides
//    fun provideClothesLocalDataSource(
//        db: NioDatabase
//    ): ClothesLocalDataSource {
//        return db.clothesLocalDataSource()
//    }
//
//    @Database(entities = [ClothesItemEntity::class], version = 1)
//    abstract class NioDatabase : RoomDatabase() {
//        abstract fun clothesLocalDataSource(): ClothesLocalDataSource
//    }
}