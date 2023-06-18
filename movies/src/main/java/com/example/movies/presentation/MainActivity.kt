package com.example.movies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetails
import com.example.movies.presentation.navigation.Screen
import com.example.movies.presentation.screens.MovieDetailsScreen
import com.example.movies.presentation.screens.MoviesScreen
import com.example.movies.presentation.screens.exampleMovieDetail
import com.example.movies.presentation.screens.exampleMoviesList
import com.example.movies.ui.theme.AIChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<MoviesViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.loadMovies()
    setContent {
      AIChallengeTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          val moviesState by viewModel.moviesState.collectAsState()
          val movieDetailsState by viewModel.movieDetailsState.collectAsState()
//          val moviesState = exampleMoviesList
//          val movieDetailsState = exampleMovieDetail
          MainScreen(
            moviesList = moviesState,
            movieDetails = movieDetailsState,
            onMovieClick = { viewModel.loadMovieDetails(it) }
          )
        }
      }
    }
  }
}

@Composable
fun MainScreen(moviesList: List<Movie>, movieDetails: MovieDetails?, onMovieClick: (id: String) -> Unit) {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = Screen.MoviesList.route) {
    composable(Screen.MoviesList.route) {
      MoviesScreen(moviesList = moviesList, onMovieClick = {
        onMovieClick(it)
        navController.navigate(Screen.MovieDetails.route)
      })
    }
    composable(Screen.MovieDetails.route) { MovieDetailsScreen(movie = movieDetails) }
  }
}

