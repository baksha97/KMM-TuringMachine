package com.example.turingmachineapp.models

class TuringMachine(val name: String, val tape: Tape, val program: Program) {

    var executions = 0

    val currentTapeState = tape.currentState
    val reel = tape.reel
    val reelPosition = tape.reelPosition

    fun nextQuadruple(): Quadruple? = program.findQuadruple(tape.currentState)

    fun hasNextQuadruple(): Boolean = nextQuadruple() != null

    fun executeSubsequentQuadruple(): TapeProcessResult = program
        .findQuadruple(tape.currentState)
        ?.let { tape.process(it) }
        .also { executions++ }
        ?: throw IllegalStateException("The program does not contain a quadruple to execute.")

}