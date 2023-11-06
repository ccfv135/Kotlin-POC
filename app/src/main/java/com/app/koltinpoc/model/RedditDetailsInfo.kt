package com.app.koltinpoc.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RedditDetailsInfo(
    @SerializedName("title")
    val title: String,
    @SerializedName("subreddit")
    val subreddit: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("num_comments")
    val commentsCount: Long,
    val readStatus: Boolean = false,
    val idElement: String = ""
) : Parcelable
