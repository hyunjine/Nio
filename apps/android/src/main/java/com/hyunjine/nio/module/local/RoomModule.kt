package com.hyunjine.nio.module.local

import dagger.Module
import dagger.hilt.InstallIn
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