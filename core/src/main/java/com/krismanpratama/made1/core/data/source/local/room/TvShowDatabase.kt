package com.krismanpratama.made1.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krismanpratama.made1.core.data.source.local.entity.TvShowEntity

@Database(entities = [TvShowEntity::class],
        version = 1,
        exportSchema = false)
abstract class TvShowDatabase : RoomDatabase() {
    abstract fun tvShowDao(): TvShowDao
    companion object {
        @Volatile
        private var INSTANCE: TvShowDatabase? = null

    }
}