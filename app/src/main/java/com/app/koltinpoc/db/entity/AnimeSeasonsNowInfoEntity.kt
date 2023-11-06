package com.app.koltinpoc.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "SEASONS_NOW")
data class AnimeSeasonsNowInfoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val articleUrl: String,
    val liveState: String?, // Cambiamos el tipo de descripción a String
    val title: String?,
    val episode: String?, // Cambiamos el tipo de estado de publicación a Boolean
    val date: String?,
    val rating: String?
)