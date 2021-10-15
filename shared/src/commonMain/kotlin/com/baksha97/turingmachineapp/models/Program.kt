package com.baksha97.turingmachineapp.models

data class Program internal constructor(
    val rawInput: String,
    val quadrupleStates: Map<State, Quadruple>
    ) {

    val size = quadrupleStates
        .values
        .toList()
        .size

    fun findQuadruple(state: State): Quadruple? = quadrupleStates[state]

}
