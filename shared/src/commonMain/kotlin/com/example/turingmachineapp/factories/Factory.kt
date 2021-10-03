package com.example.turingmachineapp.factories

import com.example.turingmachineapp.models.*
import java.lang.IllegalArgumentException

open class MachineInputException(message: String? = null) : Exception(message)

/***
 * This class is responsible for:
 *  - Creating the components of the machine from input strings.
 *  - There will be an exception thrown if the input is not properly validated at this stage.
 *
 * Ideally, we would separate this into separate factories based on how we want to take input for
 * each component.
 */
class MachineFactory {

    private fun makeCommand(input: Char): Command = when (input) {
        'L' -> Command.LEFT
        'R' -> Command.RIGHT
        '1' -> Command.FILL
        'B' -> Command.BLANK
        else -> throw IllegalArgumentException("Command not valid: $input")
    }


    private fun makeQuadruple(input: String): Quadruple {
        //ex: q1,B,R,q2
        val partition = input
            .trim()
            .split(",")
            .toTypedArray()
            .map { it.trim() }
            .takeIf { it.size == 4 }
            ?: throw MachineInputException("Invalid line - unable to partition.\n$input..")

        val start = partition[0]

        val conditional = partition[1]
            .takeIf { it.length == 1 }
            ?.first()
            ?: throw MachineInputException("Invalid conditional.\n${partition[1]}")

        val command = makeCommand(
            partition[2]
                .takeIf { it.length == 1 }
                ?.first()
                ?: throw MachineInputException("Invalid command.\n${partition[2]}")
        )

        val end = partition[3]

        val startingState = State(start, conditional)
        return Quadruple(startingState, command, end)
    }

    fun makeProgram(input: String): Program {

        val quadrupleStates = input
            .lineSequence()
            .filter { it.isNotEmpty() }
            .map { makeQuadruple(it.trim()) }
            .groupBy { it.startingState }
            .takeIf { entries -> entries.all { it.value.size == 1 } }
            ?.mapValues { it.value.first() }
            ?: throw MachineInputException("You have two of the same states in your input")

        return Program(quadrupleStates)
    }

    fun makeTape(capacity: Int, initialNumbers: List<Int> = listOf(1)): Tape {
        // calculates the amount of 1s for each number + the 0's needed for spacing
        val minAmountOfSpaceNeededOnReel = initialNumbers.sum() + initialNumbers.size + 1
        if (capacity < minAmountOfSpaceNeededOnReel)
            throw kotlin.IllegalArgumentException("There is not enough capacity supplied to support this input. $initialNumbers")

        return Tape(capacity, initialNumbers)
    }

}