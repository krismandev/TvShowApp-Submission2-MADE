package com.krismanpratama.made1.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: List<TvShowResponse>
)