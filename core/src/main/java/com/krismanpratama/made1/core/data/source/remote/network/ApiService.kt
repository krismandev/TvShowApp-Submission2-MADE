package com.krismanpratama.made1.core.data.source.remote.network


import com.krismanpratama.made1.core.data.source.remote.response.ListTvShowResponse
import com.krismanpratama.made1.core.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("tv/popular?api_key=44497bd364655ee5959016bd3e1f5fbc")
    suspend fun getTvShows(@Query("page") page: Int) : ListTvShowResponse


}