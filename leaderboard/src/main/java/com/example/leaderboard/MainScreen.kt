package com.example.leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leaderboard.entity.User
import com.example.leaderboard.ui.theme.AIChallengeTheme

@Composable
fun MainScreen(boardUsers: List<User>, onBoardChange: (index: Int) -> Unit) {
  var selectedButtonIndex by remember { mutableStateOf(0) }

  Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "Leaderboard", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
    Spacer(modifier = Modifier.height(10.dp))

    Box(
      modifier = Modifier
        .fillMaxWidth(0.85f)
        .padding(vertical = 5.dp, horizontal = 10.dp)
        .clip(RoundedCornerShape(30.dp))
        .background(Color(0xFFD2D3DA))
    ) {
      val boardTitles = listOf("Region", "National", "Global")

      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        boardTitles.forEachIndexed { index, title ->
          val isSelected = index == selectedButtonIndex

          Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
              .clickable {
                selectedButtonIndex = index
                onBoardChange(index)
              }
              .padding(8.dp)
          )
        }
      }

    }

    Spacer(modifier = Modifier.height(20.dp))
    LeadersView(users = boardUsers)
    Spacer(modifier = Modifier.height(18.dp))

    UsersListView(users = boardUsers)
  }

}

@Composable
fun LeadersView(users: List<User>) {
  Row(
    modifier = Modifier
      .fillMaxWidth(0.9f)
      .wrapContentHeight(Alignment.Bottom),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    LeadersViewItem(user = users[1], borderColor = Color.Blue)
    LeadersViewItem(user = users[0], borderColor = Color.Yellow)
    LeadersViewItem(user = users[2], borderColor = Color.Green)
  }
}


@Composable
fun LeadersViewItem(user: User, borderColor: Color) {
  Column(
    modifier = Modifier.wrapContentSize(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(id = R.drawable.ic_launcher_foreground),
      contentDescription = "",
      modifier = Modifier
        .clip(CircleShape)
        .border(width = 2.dp, color = borderColor, CircleShape)
    )
    Text(text = user.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    Text(text = user.score.toString())
    Text(text = user.userName)
  }
}

@Composable
fun UsersListView(users: List<User>) {
  Box(modifier = Modifier
    .fillMaxSize()
    .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
    .background(Color.Gray)
    ) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
      items(users.drop(3)) { user ->
        UserItemView(user = user)
      }
    }
  }

}

@Composable
fun UserItemView(user: User) {
  Row(modifier = Modifier
    .fillMaxWidth()
    .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = painterResource(id = R.drawable.ic_launcher_foreground),
      contentDescription = "",
      modifier = Modifier
        .size(30.dp)
        .clip(CircleShape)
    )
    Spacer(modifier = Modifier.width(8.dp))
    Column(verticalArrangement = Arrangement.SpaceBetween) {
      Text(text = user.name, fontWeight = FontWeight.SemiBold)
      Text(text = user.userName, fontSize = 12.sp)
    }
    Spacer(modifier = Modifier.fillMaxWidth(0.7f))
    Text(text = user.score.toString(), fontWeight = FontWeight.SemiBold, modifier = Modifier.align(Alignment.CenterVertically))
  }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
  AIChallengeTheme {
    Surface(modifier = Modifier.fillMaxSize()) {

      LeadersView(users = nationalUserState)
    }
  }
}
