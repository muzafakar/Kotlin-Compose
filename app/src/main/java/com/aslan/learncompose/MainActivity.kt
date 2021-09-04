package com.aslan.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aslan.learncompose.ui.theme.LearnComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val bsState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = { SheetContent() },
        sheetState = bsState
    ) {
        Button(
            onClick = {
                coroutineScope.launch {
                    if (bsState.isVisible) {
                        bsState.hide()
                    } else {
                        bsState.show()
                    }
                }
            }
        ) {
            Text(text = "Peek a boo!")
        }
    }
}

@Composable
fun SheetContent() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Text(text = "Hello from sheet")
    }
}

