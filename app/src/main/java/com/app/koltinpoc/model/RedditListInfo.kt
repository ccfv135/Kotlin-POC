package com.app.koltinpoc.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RedditListInfo(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("data")
    val data: RedditDetailsInfo
) : Parcelable
