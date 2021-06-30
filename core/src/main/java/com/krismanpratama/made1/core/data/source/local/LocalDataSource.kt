package com.krismanpratama.made1.core.data.source.local

import androidx.lifecycle.LiveData
import com.krismanpratama.made1.core.data.source.local.entity.TvShowEntity
import com.krismanpratama.made1.core.data.source.local.room.TvShowDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val tvShowDao: TvShowDao) {
    companion object {
        private var instance: LocalDataSource? = null


    }

    fun getTvShows(): Flow<List<TvShowEntity>> = tvShowDao.getTvShows()

    fun getFavoriteTvShows(): Flow<List<TvShowEntity>> = tvShowDao.getFavoriteTvShows()

    fun updateTvShow(tvShowEntity: TvShowEntity, newState: Boolean){
        tvShowEntity.is_favorite = newState
        tvShowDao.updateTvShow(tvShowEntity)
    }

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = tvShowDao.postTvShows(tvShows)

}