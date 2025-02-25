package com.example.lrucacheassignment.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.lrucacheassignment.data.local.DogDao
import com.example.lrucacheassignment.data.local.DogDatabase
import com.example.lrucacheassignment.data.remote.DogApi
import com.example.lrucacheassignment.data.repository.DogRepository
import com.example.lrucacheassignment.util.DogConstants
import com.example.lrucacheassignment.util.DogImageCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): DogDatabase {
        return Room.databaseBuilder(
            context.applicationContext, DogDatabase::class.java, DogConstants.APP_DB
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMangaDao(database: DogDatabase): DogDao {
        return database.dogDao()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): DogApi {
        return Retrofit.Builder().baseUrl(DogConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(context))
            .build()
            .create(DogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDogRepository(
        dogApi: DogApi, dogDao: DogDao, dogImageCache: DogImageCache
    ): DogRepository {
        return DogRepository(dogApi, dogDao, dogImageCache)
    }
}