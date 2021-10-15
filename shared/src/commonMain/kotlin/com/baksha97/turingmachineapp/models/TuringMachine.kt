package com.baksha97.turingmachineapp.models

import com.benasher44.uuid.uuid4


class TuringMachine(val name: String, val tape: Tape, val program: Program) {

    val id =  uuid4().toString()
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