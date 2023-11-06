package com.app.koltinpoc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.koltinpoc.db.dao.AnimeInfoDao
import com.app.koltinpoc.db.dao.AnimeSeasonsNowInfoDao
import com.app.koltinpoc.db.entity.ArticleEntity
import com.app.koltinpoc.db.entity.AnimeInfoEntity
import com.app.koltinpoc.db.entity.AnimeSeasonsNowInfoEntity

@Database(
    version = 1,
    entities = [ArticleEntity::class, AnimeInfoEntity::class, AnimeSeasonsNowInfoEntity::class],
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun animeInfo(): AnimeInfoDao

    abstract fun animeSeasonsNowInfo(): AnimeSeasonsNowInfoDao
}