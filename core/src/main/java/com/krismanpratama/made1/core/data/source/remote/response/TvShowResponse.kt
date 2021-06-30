package com.krismanpratama.made1.core.data.source.remote.response


import com.google.gson.annotations.SerializedName



data class TvShowResponse(
    @field:SerializedName("id")
        val id: Int,


    @field:SerializedName("original_name")
        val original_name: String,


    @field:SerializedName("overview")
        val overview: String,

    @field:SerializedName("poster_path")
        val poster_path: String,

    @field:SerializedName("backdrop_path")
        val backdrop_path: String,

    )

