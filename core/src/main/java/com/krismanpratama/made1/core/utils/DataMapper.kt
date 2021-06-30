package com.krismanpratama.made1.core.utils


import com.krismanpratama.made1.core.data.source.local.entity.TvShowEntity

import com.krismanpratama.made1.core.data.source.remote.response.TvShowResponse
import com.krismanpratama.made1.core.domain.model.TvShowModelDomain

object DataMapper {

    fun mapTvShowsResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                it.id,
                it.original_name,
                it.overview,
                it.poster_path,
                it.backdrop_path,
                false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapTvShowsEntitiesToDomain(input: List<TvShowEntity>): List<TvShowModelDomain> =
        input.map {
            TvShowModelDomain(
                it.id,
                it.original_name,
                it.overview,
                it.poster_path,
                it.backdrop_path,
                it.is_favorite
            )
        }

    fun mapTvShowDomainToEntity(input: TvShowModelDomain) = TvShowEntity(
        input.id,
        input.original_name,
        input.overview,
        input.poster_path,
        input.backdrop_path,
        input.is_favorite
    )

}