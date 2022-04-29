package com.yourcast.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yourcast.app.data.model.*
import com.yourcast.app.data.local.dao.CityDao
import com.yourcast.app.util.Converters

@Database(
    entities = [
        City::class,
        Current::class,
        Daily::class,
        FeelsLike::class,
        Hourly::class,
        Minutely::class,
        Temp::class,
        Weather::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database
        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "YourCast")
                .build()
    }
}