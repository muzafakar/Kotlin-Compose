package com.aslan.learncompose.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    var name by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = "Enter your name")
            },
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = { navController.navigate(Screen.DetailScreen.withArgs(name)) },
            modifier = Modifier.padding(top = 16.dp).align(Alignment.End)
        ) {
            Text(text = "Submit")
        }
    }
}