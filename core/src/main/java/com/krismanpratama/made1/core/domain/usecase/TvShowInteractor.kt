package com.krismanpratama.made1.core.domain.usecase

import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.krismanpratama.made1.core.domain.model.TvShowModelDomain
import com.krismanpratama.made1.core.domain.repository.ITvShowRepository

class TvShowInteractor(private val tvShowRepository: ITvShowRepository): TvShowUseCase {
    override fun getTvShows() = tvShowRepository.getTvShows(1)
    override fun getFavoriteTvShows() = tvShowRepository.getFavoriteTvShows()
    override fun setFavoriteTvShow(tvShow: TvShowModelDomain, state: Boolean) = tvShowRepository.setAsFavoriteTvShow(tvShow,state)
}

