package com.example.turingmachineapp.models

import com.example.turingmachineapp.factories.MachineInputException

class TuringMachine(val tape: Tape, val program: Program) {

    var executions = 0

    fun hasSubsequentState(): Boolean = program.findQuadruple(tape.currentState) != null

    fun executeSubsequentQuadruple(): TapeProcessResult = program
        .findQuadruple(tape.currentState)
        ?.let { tape.process(it) }
        .also { executions++ }
        ?: throw IllegalStateException("The program does not contain a quadruple to execute.")

}