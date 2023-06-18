package com.example.leaderboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.leaderboard.entity.User
import com.example.leaderboard.ui.theme.AIChallengeTheme
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AIChallengeTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          var boardIndexState by remember{ mutableStateOf(0) }
          MainScreen(boardUsers = regionUsers, onBoardChange = { boardIndexState = it} )
        }
      }
    }
  }
}


val regionUsers = arrayListOf(
  User("Jacob", "@username1", 3056),
  User("Emma", "@username2", 2500),
  User("Olivia", "@username3", 4000),
  User("Sophia", "@username4", 1500),
  User("Liam", "@username5", 2800),
  User("Ava", "@username6", 3300),
  User("Noah", "@username7", 4200),
  User("Isabella", "@username8", 1900)
)

val nationalUserState = arrayListOf(
  User("Emily", "@username9", 2600),
  User("Mia", "@username10", 3200),
  User("Ethan", "@username11", 3800),
  User("Amelia", "@username12", 2100),
  User("James", "@username13", 2900),
  User("Charlotte", "@username14", 4000),
  User("Benjamin", "@username15", 2700),
  User("Harper", "@username16", 1800)
)

val globalUsers = arrayListOf(
  User("Lucas", "@username17", 3600),
  User("Scarlett", "@username18", 2300),
  User("Alexander", "@username19", 2900),
  User("Aria", "@username20", 2400),
  User("Henry", "@username21", 3100),
  User("Luna", "@username22", 2200),
  User("Carter", "@username23", 3400),
  User("Grace", "@username24", 2800)
)
