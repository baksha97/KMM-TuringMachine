package com.example.turingmachineapp.models


data class State(val name: String, val value: Char) {
    override fun toString(): String {
        return "Q$name: $value"
    }
}