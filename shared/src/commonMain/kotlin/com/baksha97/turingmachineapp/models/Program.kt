package com.baksha97.turingmachineapp.models

data class Program(val rawInput: String, private val quadrupleStates: Map<State, Quadruple>) {

    val size = quadrupleStates
        .values
        .toList()
        .size

    fun findQuadruple(state: State): Quadruple? = quadrupleStates[state]

}
