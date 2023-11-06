package com.app.koltinpoc.api

import com.app.koltinpoc.model.AnimeInfo
import retrofit2.Response
import retrofit2.http.GET

interface TopHeadlinesApi {

    @GET("top/anime")
    suspend fun getAnimeTop(): Response<AnimeInfo>

    @GET("seasons/now")
    suspend fun getAnimeSeasonNow(): Response<AnimeInfo>
}