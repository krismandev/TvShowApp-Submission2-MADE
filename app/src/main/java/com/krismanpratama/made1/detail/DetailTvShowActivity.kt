package com.krismanpratama.made1.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.krismanpratama.made1.R
import com.krismanpratama.made1.core.domain.model.TvShowModelDomain
import com.krismanpratama.made1.databinding.ActivityDetailBinding
import com.krismanpratama.made1.core.utils.Constants
import org.koin.android.viewmodel.ext.android.viewModel

class DetailTvShowActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_TVSHOW = "extra_tv_show"
    }

    private val viewModel: DetailTvShowViewModel by viewModel()
    private lateinit var detailTvShowBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTvShowBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(detailTvShowBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        

        val detailTvShow = intent.getParcelableExtra<TvShowModelDomain>(EXTRA_TVSHOW)
        if (detailTvShow != null) {
            showDetail(detailTvShow)
        }
    }

    private fun showDetail(detailTvShow: TvShowModelDomain) {
        detailTvShowBinding.apply {
            titleReceived.text = detailTvShow?.original_name
            synopsisReceived.text = detailTvShow?.overview
        }
        supportActionBar?.title = detailTvShow?.original_name

        Glide.with(this)
                .load(Constants.BASE_POSTER_URL + detailTvShow?.poster_path)
                .transform(RoundedCorners(20))
                .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                .into(detailTvShowBinding.picturePoster)

        Glide.with(this)
                .load(Constants.BASE_POSTER_URL + detailTvShow?.backdrop_path)
                .transform(RoundedCorners(20))
                .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                .into(detailTvShowBinding.banner)
        var statusFavorite = detailTvShow.is_favorite
        setFavoriteState(statusFavorite)
        detailTvShowBinding.fab.setOnClickListener {
            statusFavorite = !statusFavorite
            viewModel.setFavoriteTvShow(detailTvShow,statusFavorite)
            setFavoriteState(statusFavorite)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun setFavoriteState(state: Boolean) {
        if (state) {
            detailTvShowBinding.fab.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_favorite))
        } else {
            detailTvShowBinding.fab.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_favorite_border_24))
        }
    }
}