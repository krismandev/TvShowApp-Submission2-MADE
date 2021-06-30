package com.krismanpratama.made1.utils

import com.krismanpratama.made1.core.data.source.local.entity.TvShowEntity

object DataDummy {



    fun generateDummyTvShows(): List<TvShowEntity> {

        val tvShows = ArrayList<TvShowEntity>()


        return tvShows
    }


    fun generateDummyTvShowFav(): List<TvShowEntity>{
        val dummyTvShow = generateDummyTvShows()
        val favTvShows = ArrayList<TvShowEntity>()

        for (item in dummyTvShow){
            if (item.is_favorite){
                favTvShows.add(item)
            }
        }

        return favTvShows

    }
}