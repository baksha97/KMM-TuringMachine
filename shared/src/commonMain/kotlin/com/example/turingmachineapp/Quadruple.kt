package com.example.turingmachineapp

import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException

class Quadruple(line: String) {
    // TODO: Remove if not needed.
    private val start: String
    private val conditional: Char

    val command: Command
    val end: String
    val startingState: State

    override fun toString(): String {
        return "Q$start, $conditional, $command, Q$end"
    }

    init {
        try {
            //ex: q1,B,R,q2
            val partition = line
                .split(",")
                .toTypedArray()
                .takeIf {
                    it.size == 4
                } ?: throw IllegalArgumentException("Invalid line - unable to partition.")

            start = partition[0]
            conditional = partition[1].single()
            command = Command.fromCharacter(partition[2].single())
            end = partition[3]
            startingState = State(start, conditional)
        } catch (e: IndexOutOfBoundsException) {
            throw IllegalArgumentException("Line does not fit format: $line")
        }
    }
}