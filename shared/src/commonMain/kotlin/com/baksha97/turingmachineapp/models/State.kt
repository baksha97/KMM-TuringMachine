package com.baksha97.turingmachineapp.models


data class State(val name: String, val value: Int) {
    constructor(name: String, value: Char) : this(name, if (value == 'B') 0 else 1)
    override fun toString(): String {
        return "Q$name: $value"
    }
}