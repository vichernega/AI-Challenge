package com.example.movies.data.network

import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

  @GET("movies")
  suspend fun getMovies(): List<Movie>

  @GET("movieDetails?")
  suspend fun getMovieDetails(@Query("id") id: String): MovieDetails
}