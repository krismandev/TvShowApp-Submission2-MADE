package com.krismanpratama.made1.tvshow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.krismanpratama.made1.R
import com.krismanpratama.made1.core.data.Resource
import com.krismanpratama.made1.core.ui.TvShowAdapter
import com.krismanpratama.made1.databinding.ActivityTvShowBinding
import com.krismanpratama.made1.detail.DetailTvShowActivity
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowActivity : AppCompatActivity() {
    private val viewModel: TvShowViewModel by viewModel()
    private lateinit var binding: ActivityTvShowBinding
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = resources.getString(R.string.tv_shows)

        val tvShowAdapter = TvShowAdapter(this)
        tvShowAdapter.onItemClick = {
            val detailIntent = Intent(this, DetailTvShowActivity::class.java)
            detailIntent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW,it)
            startActivity(detailIntent)
        }

        viewModel.tvShows.observe(this,{tvShows->
            if (tvShows != null){
                when(tvShows){
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        showLoading(false)
                        tvShowAdapter.setData(tvShows.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            Log.d("TvShowActivity",tvShows.toString())
        })

        with(binding.rvTvShow) {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    fun showLoading(state: Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btn_menu_favorite) {
            val uri = Uri.parse("tvshowapp://favorite")
            startActivity(Intent(Intent.ACTION_VIEW,uri))
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}