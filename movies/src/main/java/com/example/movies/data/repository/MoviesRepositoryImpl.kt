package com.example.movies.data.repository

import com.example.movies.data.network.MoviesApiService
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetails
import com.example.movies.domain.repository.MoviesRepository
import com.example.movies.log
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesApiService: MoviesApiService) : MoviesRepository {

  override suspend fun getMovies(): List<Movie> {
      val movies = moviesApiService.getMovies()
      log("MoviesRepositoryImpl getMovies(): moviesList = $movies")
      return movies
  }

  override suspend fun getMovieDetails(id: String): MovieDetails {
    val movieDetails = moviesApiService.getMovieDetails(id)
    log("MoviesRepositoryImpl getMovieDetails(): movieDetails = $movieDetails")
    return movieDetails
  }
}