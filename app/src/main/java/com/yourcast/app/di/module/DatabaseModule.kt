package com.yourcast.app.di.module

import android.content.Context
import com.yourcast.app.data.local.AppDatabase
import com.yourcast.app.data.local.dao.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideCityDao(appDatabase: AppDatabase): CityDao = appDatabase.cityDao()
}