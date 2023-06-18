package com.example.movies.presentation.navigation

sealed class Screen(val route: String) {
  object MoviesList : Screen("movieListScreen")
  object MovieDetails : Screen("movieDetailsScreen")
}
