package com.example.movies.domain.repository

import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetails

interface MoviesRepository {
  suspend fun getMovies(): List<Movie>
  suspend fun getMovieDetails(id: String): MovieDetails
}