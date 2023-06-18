package com.example.movies.di

import com.example.movies.data.network.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

  private val baseUrl = "https://us-central1-testmodule-12b1c.cloudfunctions.net/"

  @Provides
  @Singleton
  fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
    return OkHttpClient
      .Builder()
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(baseUrl)
      .build()
  }

  @Provides
  @Singleton
  fun provideMoviesApiService(retrofit: Retrofit) : MoviesApiService {
    return retrofit.create(MoviesApiService::class.java)
  }
}