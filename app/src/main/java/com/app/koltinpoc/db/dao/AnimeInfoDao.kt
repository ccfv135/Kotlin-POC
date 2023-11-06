package com.app.koltinpoc.db.dao

import androidx.room.*
import com.app.koltinpoc.db.entity.AnimeInfoEntity

@Dao
interface AnimeInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleEntity: AnimeInfoEntity): Long

    // NOTE - Since we are already using LIVE-DATA no need to use suspend function
    @Query("SELECT * FROM ANIME")
    fun getAllRedditInfo(): List<AnimeInfoEntity>

    @Query("DELETE FROM ANIME WHERE description = :title")
    suspend fun delete(title: String)

    @Query("DELETE FROM ANIME")
    suspend fun deleteAll()
}