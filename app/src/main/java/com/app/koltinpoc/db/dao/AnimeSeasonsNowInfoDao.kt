package com.app.koltinpoc.db.dao

import androidx.room.*
import com.app.koltinpoc.db.entity.AnimeSeasonsNowInfoEntity

@Dao
interface AnimeSeasonsNowInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleEntity: AnimeSeasonsNowInfoEntity): Long

    // NOTE - Since we are already using LIVE-DATA no need to use suspend function
    @Query("SELECT * FROM SEASONS_NOW")
    fun getAllSeasonsNowInfo(): List<AnimeSeasonsNowInfoEntity>

    @Query("DELETE FROM SEASONS_NOW WHERE title = :titleName")
    suspend fun delete(titleName: String)

    @Query("DELETE FROM seasons_now")
    suspend fun deleteAll()

    @Query("SELECT * FROM SEASONS_NOW WHERE title LIKE :title LIMIT 1")
    suspend fun findAnimeByTitle(title: String): AnimeSeasonsNowInfoEntity?

}