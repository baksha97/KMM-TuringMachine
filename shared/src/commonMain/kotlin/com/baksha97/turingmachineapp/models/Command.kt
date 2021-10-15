package com.baksha97.turingmachineapp.models

enum class Command {
    LEFT, RIGHT, FILL, BLANK;

    override fun toString(): String = when (this) {
        LEFT -> "L"
        RIGHT -> "R"
        FILL -> "1"
        BLANK -> "B"
    }
}