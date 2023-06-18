package com.example.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.MoviesRepositoryImpl
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetails
import com.example.movies.domain.enums.SortBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepositoryImpl) : ViewModel() {

  private val _moviesState: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
  val moviesState: StateFlow<List<Movie>> = _moviesState.asStateFlow()

  private val _movieDetailsState: MutableStateFlow<MovieDetails?> = MutableStateFlow(null)
  val movieDetailsState: StateFlow<MovieDetails?> = _movieDetailsState.asStateFlow()

  fun loadMovies() {
    viewModelScope.launch {
      _moviesState.value = moviesRepository.getMovies()
    }
  }

  fun loadMovieDetails(id: String) {
    viewModelScope.launch {
      _movieDetailsState.value = moviesRepository.getMovieDetails(id)
    }
  }

  fun filterMovies(minPrice: Int = 0, maxPrice: Int = 1000) {
    val filteredList = moviesState.value.filter { movie ->
      (movie.price >= minPrice) && (movie.price <= maxPrice)
    }
    _moviesState.value = filteredList
  }

  fun sortMovies(sortBy: SortBy) {
    when (sortBy) {
      SortBy.NAME -> moviesState.value.sortedBy { it.name }
      SortBy.PRICE -> moviesState.value.sortedBy { it.price }
    }
  }

}