package com.krismanpratama.made1.core.data

import com.krismanpratama.made1.core.data.source.local.LocalDataSource
import com.krismanpratama.made1.core.data.source.remote.RemoteDataSource
import com.krismanpratama.made1.core.data.source.remote.network.ApiResponse
import com.krismanpratama.made1.core.data.source.remote.response.TvShowResponse
import com.krismanpratama.made1.core.domain.model.TvShowModelDomain
import com.krismanpratama.made1.core.domain.repository.ITvShowRepository
import com.krismanpratama.made1.core.utils.AppExecutors
import com.krismanpratama.made1.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors) : ITvShowRepository {
    companion object {
        @Volatile
        private var instance: TvShowRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): TvShowRepository =
                instance ?: synchronized(this) {
                    instance ?: TvShowRepository(remoteData, localData, appExecutors).apply { instance = this }
                }
    }


    override fun getTvShows(pageParam: Int): Flow<Resource<List<TvShowModelDomain>>> {
        return object : NetworkBoundResource<List<TvShowModelDomain>, List<TvShowResponse>>() {
            public override fun loadFromDB(): Flow<List<TvShowModelDomain>> {
                return localDataSource.getTvShows().map { DataMapper.mapTvShowsEntitiesToDomain(it) }
            }
            override fun shouldFetch(data: List<TvShowModelDomain>?): Boolean =
                data == null || data.isEmpty()
            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShows(1)
            override suspend fun saveCallResult(tvShowResponseResponse: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowsResponsesToEntities(tvShowResponseResponse)
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()
    }




    override fun setAsFavoriteTvShow(tvShow: TvShowModelDomain, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute {
            localDataSource.updateTvShow(tvShowEntity, state)
        }
    }

    override fun getFavoriteTvShows(): Flow<List<TvShowModelDomain>> {
        return localDataSource.getFavoriteTvShows().map { DataMapper.mapTvShowsEntitiesToDomain(it) }
    }


}
