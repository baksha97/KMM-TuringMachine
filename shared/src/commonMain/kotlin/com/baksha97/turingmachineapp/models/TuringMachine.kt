package com.baksha97.turingmachineapp.models

import com.benasher44.uuid.uuid4


data class TuringMachine internal constructor(
    val name: String,
    private val tape: Tape,
    private val program: Program
) {

    val id = uuid4().toString()
    var executions = 0

    val reel get() = tape.reel
    val reelPosition get() = tape.reelPosition

    val currentStateName get() = tape.currentState.name
    val currentStateValue get() = tape.currentState.value

    // accessible initial configuration
    val initialNumbers get() = tape.initialNumbers
    val initialTapeSize get() = tape.reel.size
    val initialProgramInput get() = program.rawInput

    val currentIntegersOnReel get() = tape.calculateIntegersOnReel()

    fun nextQuadruple(): Quadruple? = program.findQuadruple(tape.currentState)

    fun hasNextQuadruple(): Boolean = nextQuadruple() != null

    fun executeSubsequentQuadruple(): TapeProcessResult = nextQuadruple()
        ?.let { tape.process(it) }
        .also { executions++ }
        ?: throw IllegalStateException("The program does not contain a quadruple to execute.")

}
