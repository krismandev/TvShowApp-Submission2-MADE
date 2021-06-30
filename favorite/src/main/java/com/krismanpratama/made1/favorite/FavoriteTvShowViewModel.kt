package com.krismanpratama.made1.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.krismanpratama.made1.core.domain.usecase.TvShowUseCase

class FavoriteTvShowViewModel(tvShowUseCase: TvShowUseCase) : ViewModel() {
    val favoriteTvShows = tvShowUseCase.getFavoriteTvShows().asLiveData()
}