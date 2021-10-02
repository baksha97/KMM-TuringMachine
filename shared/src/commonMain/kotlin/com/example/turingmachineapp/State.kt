package com.example.turingmachineapp


class State(private val name: String, private val value: Char) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other is State) {
            return name == other.name && value == other.value
        }
        return false
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "Q$name: $value"
    }
}