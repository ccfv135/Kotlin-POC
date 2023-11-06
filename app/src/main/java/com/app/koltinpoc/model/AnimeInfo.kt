package com.app.koltinpoc.model
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class AnimeInfo(
    @SerializedName("data")
    val data: List<AnimeData>,
    @SerializedName("pagination")
    val pagination: Pagination
): Parcelable
@Parcelize
data class AnimeData(
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("trailer")
    val trailer: Trailer,
    @SerializedName("approved")
    val approved: Boolean,
    @SerializedName("titles")
    val titles: List<Title>,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_english")
    val titleEnglish: String,
    @SerializedName("title_japanese")
    val titleJapanese: String,
    @SerializedName("title_synonyms")
    val titleSynonyms: List<String>,
    @SerializedName("type")
    val type: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("episodes")
    val episodes: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("airing")
    val airing: Boolean,
    @SerializedName("aired")
    val aired: Aired,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("scored_by")
    val scoredBy: Int,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("members")
    val members: Int,
    @SerializedName("favorites")
    val favorites: Int,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("background")
    val background: String,
    @SerializedName("season")
    val season: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("broadcast")
    val broadcast: Broadcast,
    @SerializedName("producers")
    val producers: List<Producer>,
    @SerializedName("licensors")
    val licensors: List<Producer>,
    @SerializedName("studios")
    val studios: List<Producer>,
    @SerializedName("genres")
    val genres: List<Producer>,
    @SerializedName("explicit_genres")
    val explicitGenres: List<Producer>,
    @SerializedName("themes")
    val themes: List<Producer>,
    @SerializedName("demographics")
    val demographics: List<Producer>
):Parcelable
@Parcelize
data class Images(
    @SerializedName("jpg")
    val jpg: ImageType,
    @SerializedName("webp")
    val webp: ImageType
): Parcelable
@Parcelize
data class ImageType(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("small_image_url")
    val smallImageUrl: String,
    @SerializedName("large_image_url")
    val largeImageUrl: String
):Parcelable
@Parcelize
data class Trailer(
    @SerializedName("youtube_id")
    val youtubeId: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("embed_url")
    val embedUrl: String
):Parcelable
@Parcelize
data class Title(
    @SerializedName("type")
    val type: String,
    @SerializedName("title")
    val title: String
):Parcelable
@Parcelize
data class Aired(
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("prop")
    val prop: Prop
):Parcelable
@Parcelize
data class Prop(
    @SerializedName("from")
    val from: Date,
    @SerializedName("to")
    val to: Date,
    @SerializedName("string")
    val string: String
):Parcelable
@Parcelize
data class Date(
    @SerializedName("day")
    val day: Int,
    @SerializedName("month")
    val month: Int,
    @SerializedName("year")
    val year: Int
):Parcelable
@Parcelize
data class Broadcast(
    @SerializedName("day")
    val day: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("string")
    val string: String
):Parcelable
@Parcelize
data class Producer(
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
):Parcelable
@Parcelize
data class Pagination(
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("items")
    val items: Items
):Parcelable
@Parcelize
data class Items(
    @SerializedName("count")
    val count: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("per_page")
    val perPage: Int
):Parcelable