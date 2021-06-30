package com.krismanpratama.made1.favorite.di

import com.krismanpratama.made1.favorite.FavoriteTvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteTvShowViewModel(get()) }
}