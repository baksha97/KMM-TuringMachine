package com.example.turingmachineapp.models

data class Program(private val quadrupleStates: Map<State, Quadruple>) {

    val size = quadrupleStates
        .values
        .toList()
        .size

    fun findQuadruple(state: State): Quadruple? = quadrupleStates[state]

}
