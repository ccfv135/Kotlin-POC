package com.app.koltinpoc.di

import com.app.koltinpoc.db.entity.ArticleEntity
import com.app.koltinpoc.db.entity.AnimeInfoEntity
import com.app.koltinpoc.db.entity.AnimeSeasonsNowInfoEntity
import com.app.koltinpoc.db.entity.SourceEntity
import com.app.koltinpoc.model.Aired
import com.app.koltinpoc.model.AnimeData
import com.app.koltinpoc.model.Article
import com.app.koltinpoc.model.Broadcast
import com.app.koltinpoc.model.Date
import com.app.koltinpoc.model.ImageType
import com.app.koltinpoc.model.Images
import com.app.koltinpoc.model.Prop
import com.app.koltinpoc.model.Source
import com.app.koltinpoc.model.Title
import com.app.koltinpoc.model.Trailer

/*
* This is a transformer class
* We cannot just use our model classes for inserting or getting data from db
* There might be a time when variables might be added or removed in model classes
* So it is always a better practice to have transformer classes
* */
object Transformer {

    fun convertAnimeDataToAnimeInfoEntity(animeData: AnimeData): AnimeInfoEntity {
        return AnimeInfoEntity(
            articleUrl = animeData.images.jpg.imageUrl,
            title = animeData.title,
            description = animeData.source,
            publishedState = animeData.airing,
        )
    }

    fun convertAnimeSeasonsNowDataToAnimeInfoEntity(animeData: AnimeData): AnimeSeasonsNowInfoEntity {
        return AnimeSeasonsNowInfoEntity(
            articleUrl = animeData.images.jpg.imageUrl,
            title = animeData.title,
            liveState = if (animeData.airing) "Airing" else "Not Airing",
            episode = animeData.episodes.toString(),
            date = animeData.aired.toString(),
            rating = animeData.rating
        )
    }

    fun convertAnimeInfoEntitiesListToAnimeDataList(animeEntities: List<AnimeSeasonsNowInfoEntity>): List<AnimeData> {
        return animeEntities.map { entity ->
            AnimeData(
                malId = 0,
                url = entity.articleUrl ?: "",
                images = Images(
                    jpg = ImageType(
                        imageUrl = entity.articleUrl,
                        smallImageUrl = "",
                        largeImageUrl = ""
                    ),
                    webp = ImageType(
                        imageUrl = "",
                        smallImageUrl = "",
                        largeImageUrl = ""
                    )
                ),
                trailer = Trailer(
                    youtubeId = "",
                    url = "",
                    embedUrl = ""
                ),
                approved = false,
                titles = listOf(
                    Title(
                        title = "",
                        type = "",
                    )
                ),
                title = entity.title ?: "",
                titleEnglish = "",
                titleJapanese = "",
                titleSynonyms = listOf(),
                type = "",
                source = "",
                episodes = entity.episode?.toInt() ?: 0,
                status = "",
                airing = entity.liveState == "Airing",
                aired = Aired(
                    from = "",
                    to = "",
                    prop = Prop(
                        from = Date(
                            day = 0,
                            month = 0,
                            year = 0
                        ),
                        to = Date(
                            day = 0,
                            month = 0,
                            year = 0
                        ),
                        string = ""
                    )
                ),
                duration = "",
                rating = entity.rating ?: "No rating",
                scoredBy = 0,
                rank = 0,
                popularity = 0,
                members = 0,
                favorites = 0,
                synopsis = "",
                background = "",
                season = "",
                year = 0,
                broadcast = Broadcast(
                    day = "",
                    time = "",
                    timezone = "",
                    string = ""
                ),
                producers = listOf(),
                licensors = listOf(),
                studios = listOf(),
                genres = listOf(),
                explicitGenres = listOf(),
                themes = listOf(),
                demographics = listOf()
            )
        }
    }

    fun convertAnimeInfoEntitiesToAnimeData(animeEntities: AnimeSeasonsNowInfoEntity): AnimeData {
        return animeEntities.let { entity ->
            AnimeData(
                malId = 0,
                url = entity.articleUrl ?: "",
                images = Images(
                    jpg = ImageType(
                        imageUrl = entity.articleUrl,
                        smallImageUrl = "",
                        largeImageUrl = ""
                    ),
                    webp = ImageType(
                        imageUrl = "",
                        smallImageUrl = "",
                        largeImageUrl = ""
                    )
                ),
                trailer = Trailer(
                    youtubeId = "",
                    url = "",
                    embedUrl = ""
                ),
                approved = false,
                titles = listOf(
                    Title(
                        title = "",
                        type = "",
                    )
                ),
                title = entity.title ?: "",
                titleEnglish = "",
                titleJapanese = "",
                titleSynonyms = listOf(),
                type = "",
                source = "",
                episodes = entity.episode?.toInt() ?: 0,
                status = "",
                airing = entity.liveState == "Airing",
                aired = Aired(
                    from = "",
                    to = "",
                    prop = Prop(
                        from = Date(
                            day = 0,
                            month = 0,
                            year = 0
                        ),
                        to = Date(
                            day = 0,
                            month = 0,
                            year = 0
                        ),
                        string = ""
                    )
                ),
                duration = "",
                rating = entity.rating ?: "No rating",
                scoredBy = 0,
                rank = 0,
                popularity = 0,
                members = 0,
                favorites = 0,
                synopsis = "",
                background = "",
                season = "",
                year = 0,
                broadcast = Broadcast(
                    day = "",
                    time = "",
                    timezone = "",
                    string = ""
                ),
                producers = listOf(),
                licensors = listOf(),
                studios = listOf(),
                genres = listOf(),
                explicitGenres = listOf(),
                themes = listOf(),
                demographics = listOf()
            )
        }
    }



    fun convertEntityRedditListToAnimeDataList(animeInfoListEntity: List<AnimeInfoEntity>): List<AnimeData> {
        return animeInfoListEntity.map {
            AnimeData(
                malId = it.id, // Supongo que 'id' es el equivalente al campo 'malId' en tu entidad
                url = it.articleUrl ?: "",
                images = Images(
                    jpg = ImageType(
                        imageUrl = it.articleUrl ?: "",
                        smallImageUrl = "",
                        largeImageUrl = ""
                    ),
                    webp = ImageType(
                        imageUrl = it.articleUrl ?: "",
                        smallImageUrl = "",
                        largeImageUrl = ""
                    )
                ),
                trailer = Trailer(
                    youtubeId = "",
                    url = "",
                    embedUrl = ""
                ),
                approved = it.publishedState ?: false,
                titles = listOf(
                    Title(
                        type = "",
                        title = it.title ?: ""
                    )
                ),
                title = it.title ?: "",
                titleEnglish = "",
                titleJapanese = "",
                titleSynonyms = listOf(),
                type = "",
                source = "",
                episodes = 0,
                status = "",
                airing = false,
                aired = Aired(
                    from = "",
                    to = "",
                    prop = Prop(
                        from = Date(0, 0, 0),
                        to = Date(0, 0, 0),
                        string = ""
                    )
                ),
                duration = "",
                rating = "",
                scoredBy = 0,
                rank = 0,
                popularity = 0,
                members = 0,
                favorites = 0,
                synopsis = "",
                background = "",
                season = "",
                year = 0,
                broadcast = Broadcast(
                    day = "",
                    time = "",
                    timezone = "",
                    string = ""
                ),
                producers = listOf(),
                licensors = listOf(),
                studios = listOf(),
                genres = listOf(),
                explicitGenres = listOf(),
                themes = listOf(),
                demographics = listOf()
            )
        }
    }

    fun convertArticleModelToArticleEntity(article: Article): ArticleEntity {
        return ArticleEntity(
            author = article.author,
            content = article.content,
            source = convertSourceModelToSourceEntity(article.source),
            description = article.description,
            publishedAt = article.publishedAt,
            url = article.url,
            urlToImage = article.urlToImage,
            title = article.title

        )
    }

    fun convertArticleEntityToArticleModel(articleEntity: ArticleEntity): Article {
        return Article(
            author = articleEntity.author,
            content = articleEntity.content,
            source = convertSourceEntityToSourceModel(articleEntity.source),
            description = articleEntity.description,
            publishedAt = articleEntity.publishedAt,
            url = articleEntity.url,
            urlToImage = articleEntity.urlToImage,
            title = articleEntity.title

        )
    }

    private fun convertSourceModelToSourceEntity(source: Source?): SourceEntity? {

        source?.let {
            return SourceEntity(source.id, source.name)
        }

        return null
    }

    private fun convertSourceEntityToSourceModel(sourceEntity: SourceEntity?): Source? {

        sourceEntity?.let {

            return Source(sourceEntity.id, sourceEntity.name)
        }

        return null
    }
}