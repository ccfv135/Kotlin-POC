package com.app.koltinpoc.di

import com.app.koltinpoc.db.AppDatabase
import com.app.koltinpoc.di.Transformer.convertAnimeDataToAnimeInfoEntity
import com.app.koltinpoc.di.Transformer.convertAnimeInfoEntitiesListToAnimeDataList
import com.app.koltinpoc.di.Transformer.convertAnimeInfoEntitiesToAnimeData
import com.app.koltinpoc.di.Transformer.convertAnimeSeasonsNowDataToAnimeInfoEntity
import com.app.koltinpoc.di.Transformer.convertEntityRedditListToAnimeDataList
import com.app.koltinpoc.model.AnimeData
import com.app.koltinpoc.model.AnimeInfo
import com.app.koltinpoc.model.RedditListInfo
import com.app.koltinpoc.utils.DataHandler
import java.io.IOException
import javax.inject.Inject

class DBRepository @Inject constructor(val appDatabase: AppDatabase) {

    suspend fun deleteElementByName(redditListInfo: RedditListInfo): DataHandler<Unit> {
        return try {
            appDatabase.animeInfo().delete(redditListInfo.data.subreddit)
            DataHandler.SUCCESS(Unit)
        } catch (e: IOException) {
            DataHandler.ERROR(message = "Could not delete element")
        }
    }

    suspend fun deleteAllElements(): DataHandler<Unit> {
        return try {
            appDatabase.animeInfo().deleteAll()
            DataHandler.SUCCESS(Unit)
        } catch (e: IOException) {
            DataHandler.ERROR(message = "could not delete all elements")
        }
    }


    suspend fun insertAnimeInfo(animeInfo: AnimeInfo): DataHandler<Unit> {
        return try {
            animeInfo.data.forEach {
                appDatabase.animeInfo()
                    .insert(convertAnimeDataToAnimeInfoEntity(it))
            }
            DataHandler.SUCCESS(Unit)
        } catch (e: IOException) {
            DataHandler.ERROR(message = "Could not save items")
        }
    }

    suspend fun insertAnimeSeasonsNowInfo(animeInfo: AnimeInfo): DataHandler<Unit> {
        return try {
            animeInfo.data.forEach {
                appDatabase.animeSeasonsNowInfo()
                    .insert(convertAnimeSeasonsNowDataToAnimeInfoEntity(it))
            }
            DataHandler.SUCCESS(Unit)
        } catch (e: IOException) {
            DataHandler.ERROR(message = "Could not save items")
        }
    }

    suspend fun searchAnimeByTitle(title: String): DataHandler<AnimeData> {
        return try {
            val anime = appDatabase.animeSeasonsNowInfo().findAnimeByTitle(title)
            anime?.let {
                DataHandler.SUCCESS(convertAnimeInfoEntitiesToAnimeData(it))
            } ?: DataHandler.ERROR(message = "Anime no encontrado")
        } catch (e: Exception) {
            DataHandler.ERROR(message = "Ocurrió un error durante la búsqueda")
        }
    }


    suspend fun getAllAnimeInfo(): List<AnimeData> {
        return convertEntityRedditListToAnimeDataList(appDatabase.animeInfo().getAllRedditInfo())
    }

    suspend fun getAllSeasonsAnimeInfo(): List<AnimeData> {
        return convertAnimeInfoEntitiesListToAnimeDataList(
            appDatabase.animeSeasonsNowInfo().getAllSeasonsNowInfo()
        )
    }
}