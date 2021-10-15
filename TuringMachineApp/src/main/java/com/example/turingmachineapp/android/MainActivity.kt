package com.example.turingmachineapp.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.turingmachineapp.Greeting
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }
}

@Preview
@Composable
fun Screen() {
    Text(text = "hello")
}