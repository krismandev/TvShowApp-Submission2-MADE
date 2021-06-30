package com.krismanpratama.made1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.krismanpratama.made1.tvshow.TvShowActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, TvShowActivity::class.java))
            finish()
        },2000)
    }
}