package com.baksha97.turingmachineapp.models

data class Quadruple internal constructor(
    val startingState: State,
    val command: Command,
    val end: String
    )