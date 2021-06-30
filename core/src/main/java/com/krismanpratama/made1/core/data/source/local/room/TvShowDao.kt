package com.krismanpratama.made1.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.krismanpratama.made1.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tv_shows")
    fun getTvShows(): Flow<List<TvShowEntity>>


    @Query("SELECT * FROM tv_shows WHERE tv_shows.is_favorite = 1")
    fun getFavoriteTvShows(): Flow<List<TvShowEntity>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun postTvShows(tvShows: List<TvShowEntity>)


    @Update
    fun updateTvShow(tvShowEntity: TvShowEntity)
}