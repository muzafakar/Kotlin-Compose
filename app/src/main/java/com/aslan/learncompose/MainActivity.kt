package com.aslan.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslan.learncompose.QuestionState.*
import com.aslan.learncompose.ui.theme.LearnComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}

sealed class Question(val title: String, val color: Color) {
    object Berjamaah : Question("Berjamaah", Color.Green)
    object Sendirian : Question("Sendirian", Color.Cyan)
    object Berhalangan : Question("Berhalangan", Color.Magenta)
    object Terlewati : Question("Terlewati", Color.Red)
}

enum class QuestionState {
    INIT, SECOND, THIRD
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    var questionState: QuestionState by remember {
        mutableStateOf(INIT)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(15.dp)),
            backgroundColor = MaterialTheme.colors.primaryVariant
        ) {

            AnimatedVisibility(
                visible = questionState == INIT,
                enter = expandHorizontally(),
                exit = shrinkHorizontally(shrinkTowards = Alignment.Start)
            ) {
                PrayerCardContent { questionState = SECOND }
            }

            AnimatedVisibility(
                visible = questionState == SECOND,
                enter = expandHorizontally(),
                exit = shrinkHorizontally(shrinkTowards = Alignment.End)
            ) {
                Question { questionState = INIT }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrayerCardPreview() {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(15.dp)),
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        PrayerCardContent {

        }
    }
}

@Composable
fun PrayerCardContent(onClick: () -> Unit) {
    BoxWithConstraints(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Sudah Shalat Maghrib?",
            modifier = Modifier.align(Alignment.TopStart),
            style = MaterialTheme.typography.h1,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Column(modifier = Modifier.align(Alignment.CenterStart)) {
            Text(
                text = "Aikmel",
                style = MaterialTheme.typography.h1,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Maghrib pukul 18:13 di Aikmel",
                style = MaterialTheme.typography.body1,
            )
        }

        Text(
            text = "Sudah",
            modifier = Modifier
                .clickable { onClick() }
                .align(Alignment.BottomEnd)
                .padding(horizontal = 6.dp, vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionPreview() {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(15.dp)),
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Question {}
    }
}

@Composable
fun Question(onClick: () -> Unit) {
    val items = listOf(
        Question.Berjamaah,
        Question.Sendirian,
        Question.Berhalangan,
        Question.Terlewati,
    )

    Row(modifier = Modifier.fillMaxWidth()) {
        items.forEach {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { onClick() }
                    .background(it.color),
                contentAlignment = Alignment.Center
            ) {
                Text(text = it.title)

            }
        }
    }
}



