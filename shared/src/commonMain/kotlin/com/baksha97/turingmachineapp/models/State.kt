package com.baksha97.turingmachineapp.models


data class State internal constructor(val name: String, val value: Int) {

    constructor(name: String, value: Char) : this(name, if (value == 'B') 0 else 1)

    override fun toString() = "Q$name: $value"

}