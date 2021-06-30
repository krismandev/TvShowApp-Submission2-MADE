package com.krismanpratama.made1.core.di

import androidx.room.Room
import com.krismanpratama.made1.core.data.TvShowRepository
import com.krismanpratama.made1.core.data.source.local.LocalDataSource
import com.krismanpratama.made1.core.data.source.local.room.TvShowDatabase
import com.krismanpratama.made1.core.data.source.remote.RemoteDataSource
import com.krismanpratama.made1.core.data.source.remote.network.ApiService
import com.krismanpratama.made1.core.domain.repository.ITvShowRepository
import com.krismanpratama.made1.core.utils.AppExecutors
import com.krismanpratama.made1.core.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<TvShowDatabase>().tvShowDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TvShowDatabase::class.java, "tv_show.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITvShowRepository> { TvShowRepository(get(), get(), get()) }
}