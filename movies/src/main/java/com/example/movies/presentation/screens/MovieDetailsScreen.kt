package com.example.movies.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movies.domain.entities.MovieDetails
import com.google.gson.Gson

@Composable
fun MovieDetailsScreen(movie: MovieDetails?) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(10.dp)
      .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    if (movie != null) {
      AsyncImage(model = movie.image, contentDescription = movie.name, modifier = Modifier.align(Alignment.CenterHorizontally))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = movie.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = movie.price.toString(), fontWeight = FontWeight.Bold, fontSize = 18.sp)
      }
      Text(text = movie.meta, fontWeight = FontWeight.Bold)
      Text(text = movie.rating, fontWeight = FontWeight.Bold)
      Text(text = movie.synopsis)
    }
  }
}

const val movieDetailsJson =
  "{\"image\":\"https://firebasestorage.googleapis.com/v0/b/testmodule-12b1c.appspot.com/o/friends.png?alt=media&token=82b88dc8-8995-4892-b208-6b530ce253b4\",\"meta\":\"2003, Sitcoms, TV Comedies, US TV Shows\",\"name\":\"Friends\",\"price\":230,\"rating\":\"Witty, Quirky\",\"synopsis\":\"Friends is an American television sitcom created by David Crane and Marta Kauffman, which aired on NBC from September 22, 1994, to May 6, 2004, lasting ten seasons. With an ensemble cast starring Jennifer Aniston, Courteney Cox, Lisa Kudrow, Matt LeBlanc, Matthew Perry and David Schwimmer, the show revolves around six friends in their 20s and 30s who live in Manhattan, New York City. The series was produced by Bright/Kauffman/Crane Productions, in association with Warner Bros. Television. The original executive producers were Kevin S. Bright, Kauffman, and Crane. Kauffman and Crane began developing Friends under the working title Insomnia Cafe between November and December 1993. They presented the idea to Bright, and together they pitched a seven-page treatment of the show to NBC. After several script rewrites and changes, including title changes to Six of One and Friends Like Us, the series was finally named Friends. Filming took place at Warner Bros. Studios in Burbank, California. The entire series ranked within the top ten of the final television season ratings; it ultimately reached the number-one spot in its eighth season. The series finale aired on May 6, 2004, and was watched by around 52.5 million American viewers, making it the fifth-most-watched series finale in television history and the most-watched television episode of the 2000s.\"}"

val exampleMovieDetail = Gson().fromJson(movieDetailsJson, MovieDetails::class.java)

