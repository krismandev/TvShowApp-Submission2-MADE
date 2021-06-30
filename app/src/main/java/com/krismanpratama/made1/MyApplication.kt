package com.krismanpratama.made1

import android.app.Application
import com.krismanpratama.made1.core.di.databaseModule
import com.krismanpratama.made1.core.di.networkModule
import com.krismanpratama.made1.core.di.repositoryModule
import com.krismanpratama.made1.di.useCaseModule
import com.krismanpratama.made1.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}