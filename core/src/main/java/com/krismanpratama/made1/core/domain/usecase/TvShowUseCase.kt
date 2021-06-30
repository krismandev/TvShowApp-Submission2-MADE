package com.krismanpratama.made1.core.domain.usecase

import androidx.lifecycle.LiveData
import com.krismanpratama.made1.core.data.Resource

import com.krismanpratama.made1.core.domain.model.TvShowModelDomain
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getTvShows(): Flow<Resource<List<TvShowModelDomain>>>
    fun getFavoriteTvShows(): Flow<List<TvShowModelDomain>>
    fun setFavoriteTvShow(tvShow: TvShowModelDomain, state: Boolean)
}