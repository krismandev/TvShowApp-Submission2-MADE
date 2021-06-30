package com.krismanpratama.made1.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.krismanpratama.made1.core.domain.usecase.TvShowUseCase

class TvShowViewModel(tvShowUseCase: TvShowUseCase) : ViewModel(){
    val tvShows = tvShowUseCase.getTvShows().asLiveData()
}