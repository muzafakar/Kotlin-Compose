package com.aslan.learncompose.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(name: String?) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello, $name", modifier = Modifier.align(Alignment.Center))
    }
}