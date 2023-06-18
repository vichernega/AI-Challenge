package com.example.movies.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.R
import com.example.movies.domain.entities.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun MoviesScreen(moviesList: List<Movie>, onMovieClick: (id: String) -> Unit) {
  Column(modifier = Modifier
    .fillMaxSize()
    .padding(10.dp)) {
    Spacer(modifier = Modifier.height(10.dp))

    /*Row(horizontalArrangement = Arrangement.End) {
      FilterView()
      Spacer(modifier = Modifier.width(10.dp))
      SortView()
    }*/

    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
      items(moviesList) { movie ->
        MovieItem(movie = movie) {
          onMovieClick(it)
        }
      }
    }
  }
}

@Composable
fun MovieItem(movie: Movie, onClick: (id: String) -> Unit) {
  Box(modifier = Modifier
    .fillMaxWidth()
    .wrapContentHeight()
    .clickable { onClick(movie.id) }
    .padding(10.dp)) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(text = movie.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
      Text(text = movie.price.toString(), fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
  }
}

@Composable
fun FilterView() {
  Row(modifier = Modifier
    .fillMaxWidth()
    .padding(5.dp)
    .clickable {

    }
  ) {
    Image(painter = painterResource(id = R.drawable.ic_filter), contentDescription = "")
    Spacer(modifier = Modifier.width(3.dp))
    Text(text = "Filter")
  }
}

@Composable
fun SortView() {
  Row(modifier = Modifier
    .fillMaxWidth()
    .padding(5.dp)
    .clickable {

    }
  ) {
    Image(painter = painterResource(id = R.drawable.ic_sort), contentDescription = "")
    Spacer(modifier = Modifier.width(3.dp))
    Text(text = "Sort")
  }
}

const val moviesListJson =
  "[{\"id\":\"0\",\"name\":\"Rick and Morty\",\"price\":100},{\"id\":\"1\",\"name\":\"Friends\",\"price\":230},{\"id\":\"2\",\"name\":\"The Big Lebovski\",\"price\":120},{\"id\":\"3\",\"name\":\"Don't look up\",\"price\":800},{\"id\":\"4\",\"name\":\"Stranger Things\",\"price\":450},{\"id\":\"5\",\"name\":\"Black Mirror\",\"price\":1000},{\"id\":\"6\",\"name\":\"Dark\",\"price\":550},{\"id\":\"7\",\"name\":\"Meet Joe Black\",\"price\":330},{\"id\":\"8\",\"name\":\"The Matrix\",\"price\":700},{\"id\":\"9\",\"name\":\"Ghost in the Shell\",\"price\":256},{\"id\":\"10\",\"name\":\"No Country for Old Men\",\"price\":700},{\"id\":\"11\",\"name\":\"Blade Runner 2049\",\"price\":500},{\"id\":\"12\",\"name\":\"Watchmen\",\"price\":330},{\"id\":\"13\",\"name\":\"The Big Bang Theory\",\"price\":200},{\"id\":\"14\",\"name\":\"Better Call Saul\",\"price\":440},{\"id\":\"15\",\"name\":\"The Queen's Gambit\",\"price\":700},{\"id\":\"16\",\"name\":\"South Park\",\"price\":200},{\"id\":\"17\",\"name\":\"Space Force\",\"price\":900},{\"id\":\"18\",\"name\":\"Breaking Bad\",\"price\":1000},{\"id\":\"19\",\"name\":\"The Great Gatsby\",\"price\":50}]"

val exampleMoviesList: List<Movie> = Gson().fromJson(moviesListJson, object : TypeToken<List<Movie>>() {}.type)

