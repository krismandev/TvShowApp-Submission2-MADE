package com.krismanpratama.made1.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.krismanpratama.made1.core.data.source.remote.network.ApiResponse
import com.krismanpratama.made1.core.data.source.remote.network.ApiService
import com.krismanpratama.made1.core.data.source.remote.response.ListTvShowResponse
import com.krismanpratama.made1.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception



class RemoteDataSource(private val apiService: ApiService){
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private const val TAG = "RemoteDataSource"

    }

    suspend fun getTvShows(pageParam: Int): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow{
            try {
                val tvShowsResponse = apiService.getTvShows(pageParam)
                val arrData = tvShowsResponse.results
                if (arrData.isNotEmpty()){
                    emit(ApiResponse.Success(tvShowsResponse.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }



}
