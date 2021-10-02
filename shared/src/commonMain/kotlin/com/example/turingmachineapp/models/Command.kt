package com.example.turingmachineapp.models
import java.lang.IllegalArgumentException

enum class Command(c: Char) {
    LEFT('L'), RIGHT('R'), FILL('1'), BLANK('B');

    override fun toString(): String = when (this) {
        LEFT -> "L"
        RIGHT -> "R"
        FILL -> "1"
        BLANK -> "B"
    }

}