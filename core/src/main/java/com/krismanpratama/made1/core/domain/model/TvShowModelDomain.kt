package com.krismanpratama.made1.core.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowModelDomain(
    val id: Int,
    val original_name: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    var is_favorite: Boolean
):Parcelable