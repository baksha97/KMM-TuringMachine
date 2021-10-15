package com.baksha97.turingmachineapp.android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baksha97.turingmachineapp.models.TuringMachine


@Preview
@Composable
fun CreateScreen(onCreate: (TuringMachine) -> Unit = {}) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val lineHeight = MaterialTheme.typography.h2.fontSize * 4 / 3
        val expectedNumberOfLines = 5
        var lines by remember { mutableStateOf(0) }

        var nameState by remember { mutableStateOf(TextFieldValue()) }
        var numbersOnTapeState by remember { mutableStateOf(TextFieldValue()) }
        val sliderPosition = remember { mutableStateOf(0f) }
        val programInput = remember { mutableStateOf(TextFieldValue()) }
        TopAppBar(
            // in below line we are
            // adding title to our top bar.
            title = {
                // inside title we are
                // adding text to our toolbar.
                Text(
                    text = "Machine Configuration",
                    // below line is use
                    // to give text color.
                    color = Color.White
                )
            },
            navigationIcon = {
                // navigation icon is use
                // for drawer icon.
//                AppBarIcon(
//                    icon = imageResource(
//                        id = R.drawable.ic_menu_black_24dp)
//                ) {
//                    // Open nav drawer
//                }
            },
            // below line is use to give background color
            backgroundColor = Color.Black,

            // content color is use to give
            // color to our content in our toolbar.
            contentColor = Color.White,

            // below line is use to give
            // elevation to our toolbar.
            elevation = 12.dp
        )
        TextField(
            placeholder = { Text(text = "Name") },
            modifier = Modifier.padding(16.dp),
            value = nameState,
            onValueChange = { nameState = it }
        )
        TextField(
            placeholder = { Text(text = "Initial Tape Numbers") },
            modifier = Modifier.padding(16.dp),
            value = numbersOnTapeState,
            onValueChange = { numbersOnTapeState = it }
        )
        Slider(
            value = sliderPosition.value,
            modifier = Modifier.padding(16.dp),
            onValueChange = { sliderPosition.value = it }
        )
        TextField(
            placeholder = { Text(text = "Program Input") },
            value = programInput.value,
            onValueChange = { programInput.value = it },
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(minHeight = with(LocalDensity.current) {
                    (lineHeight * expectedNumberOfLines).toDp()
                }
                ),
        )
//        //alt
//        TextField(
//            placeholder = { Text(text = "Program Input") },
//            value = programInput.value,
//            onValueChange = { programInput.value = it },
//            onTextLayout = { res -> lines = res.lineCount }
//        )
    }
}
