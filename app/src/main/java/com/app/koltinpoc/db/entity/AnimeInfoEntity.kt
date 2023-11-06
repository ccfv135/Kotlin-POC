package com.app.koltinpoc.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ANIME")
data class AnimeInfoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val articleUrl: String?,
    val title: String?,
    val description: String?, // Cambiamos el tipo de descripción a String
    val publishedState: Boolean?, // Cambiamos el tipo de estado de publicación a Boolean
)