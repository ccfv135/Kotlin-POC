package com.app.koltinpoc.model

import com.google.gson.annotations.SerializedName

data class RedditData(
    @SerializedName("after")
    val after: String,
    @SerializedName("children")
    val children: List<RedditListInfo>
)
