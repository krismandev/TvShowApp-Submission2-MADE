package com.krismanpratama.made1.di

import com.krismanpratama.made1.core.domain.usecase.TvShowInteractor
import com.krismanpratama.made1.core.domain.usecase.TvShowUseCase
import com.krismanpratama.made1.detail.DetailTvShowViewModel
import com.krismanpratama.made1.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TvShowUseCase> { TvShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
}