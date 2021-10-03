package com.example.turingmachineapp.models

enum class Command(c: Char) {
    LEFT('L'), RIGHT('R'), FILL('1'), BLANK('B');

    override fun toString(): String = when (this) {
        LEFT -> "L"
        RIGHT -> "R"
        FILL -> "1"
        BLANK -> "B"
    }

}