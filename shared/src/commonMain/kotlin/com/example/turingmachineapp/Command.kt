package com.example.turingmachineapp
import java.lang.IllegalArgumentException

enum class Command {
    LEFT, RIGHT, FILL, BLANK;

    override fun toString(): String = when (this) {
        LEFT -> "L"
        RIGHT -> "R"
        FILL -> "1"
        BLANK -> "B"
    }


    companion object {
        fun fromCharacter(c: Char): Command = when (c) {
            'L' -> LEFT
            'R' -> RIGHT
            '1' -> FILL
            'B' -> BLANK
            else -> throw IllegalArgumentException("Command not valid: $c")
        }
    }
}