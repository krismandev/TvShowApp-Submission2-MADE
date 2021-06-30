package com.krismanpratama.made1.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.krismanpratama.made1.core.ui.TvShowAdapter
import com.krismanpratama.made1.detail.DetailTvShowActivity
import com.krismanpratama.made1.favorite.databinding.ActivityFavoriteBinding
import com.krismanpratama.made1.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val viewModel: FavoriteTvShowViewModel by viewModel()
    private lateinit var  favoriteTvShowBinding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteTvShowBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteTvShowBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = resources.getString(R.string.favorite)

        loadKoinModules(favoriteModule)
        val tvShowAdapter = TvShowAdapter(this)

        showLoading(true)
        viewModel.favoriteTvShows.observe(this,{tvShows->
            tvShowAdapter.setData(tvShows)
            favoriteTvShowBinding.notFound.visibility = if (tvShows.isNotEmpty()) View.GONE else View.GONE
            showLoading(false)
        })

        with(favoriteTvShowBinding.rvTvShow) {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        tvShowAdapter.onItemClick = {
            val detailIntent = Intent(this, DetailTvShowActivity::class.java)
            detailIntent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW,it)
            startActivity(detailIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun showLoading(state: Boolean){
        if(state){
            favoriteTvShowBinding.progressBar.visibility = View.VISIBLE
        }else{
            favoriteTvShowBinding.progressBar.visibility = View.GONE
        }
    }
}