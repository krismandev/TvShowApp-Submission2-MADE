package com.krismanpratama.made1.core.domain.repository

import androidx.lifecycle.LiveData
import com.krismanpratama.made1.core.data.Resource

import com.krismanpratama.made1.core.domain.model.TvShowModelDomain
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {

    fun getTvShows(pageParam: Int): Flow<Resource<List<TvShowModelDomain>>>
    fun setAsFavoriteTvShow(tvShow: TvShowModelDomain, state: Boolean)
    fun getFavoriteTvShows(): Flow<List<TvShowModelDomain>>
}