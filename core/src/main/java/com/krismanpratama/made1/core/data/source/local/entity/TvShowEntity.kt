package com.krismanpratama.made1.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TvShowEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "original_name")
    val original_name: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val poster_path: String,

    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String,

    @ColumnInfo(name = "is_favorite")
    var is_favorite: Boolean
    )