package com.app.koltinpoc.di

import com.app.koltinpoc.api.TopHeadlinesApi
import com.app.koltinpoc.model.AnimeInfo
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    val topHeadlinesApi: TopHeadlinesApi
) {

    suspend fun getAnimeTop(): Response<AnimeInfo> {
        return topHeadlinesApi.getAnimeTop()
    }

    suspend fun getAnimeSeasonsNow(): Response<AnimeInfo> {
        return topHeadlinesApi.getAnimeSeasonNow()
    }

}