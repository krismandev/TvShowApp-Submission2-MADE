package com.krismanpratama.made1.detail

import androidx.lifecycle.ViewModel
import com.krismanpratama.made1.core.domain.model.TvShowModelDomain
import com.krismanpratama.made1.core.domain.usecase.TvShowUseCase


class DetailTvShowViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel(){
    fun setFavoriteTvShow(tvShow: TvShowModelDomain, newState: Boolean) =
        tvShowUseCase.setFavoriteTvShow(tvShow,newState)
}