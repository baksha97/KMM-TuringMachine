package com.example.turingmachineapp.models

class TuringMachine(private val tape: Tape, private val program: Program) {

    var executions = 0

    val currentTapeState = tape.currentState
    val reel = tape.reel
    val reelPosition = tape.reelPosition

    fun hasSubsequentState(): Boolean = program.findQuadruple(tape.currentState) != null

    fun executeSubsequentQuadruple(): TapeProcessResult = program
        .findQuadruple(tape.currentState)
        ?.let { tape.process(it) }
        .also { executions++ }
        ?: throw IllegalStateException("The program does not contain a quadruple to execute.")

}