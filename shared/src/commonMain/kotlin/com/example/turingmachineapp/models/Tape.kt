package com.example.turingmachineapp.models

import com.example.turingmachineapp.utils.countsOfConsecutiveEquality

open class TapeExecutionException(message: String? = null) : Exception(message)

sealed class TapeProcessResult(val endingState: State) {
    class MovementFailure(val index: Int, endingState: State) : TapeProcessResult(endingState)
    class MovementSuccess(val newPosition: Int, endingState: State) : TapeProcessResult(endingState)
    class WriteSuccess(val index: Int, endingState: State) : TapeProcessResult(
        endingState
    )
}

data class Tape(private val capacity: Int, private val initialNumbers: List<Int> = listOf(1, 1)) {

    var currentState: State = State(INITIAL_QUADRUPLE_STATE_NAME, BLANK)
    var reelPosition: Int
    val reel: MutableList<Char>

    override fun toString(): String {
        return reel.toString()
    }
    init {
        // calculates the amount of 1s for each number + the 0's needed for spacing
        val minAmountOfSpaceNeededOnReel = initialNumbers.sum() + initialNumbers.size + 1
        if (capacity < minAmountOfSpaceNeededOnReel)
            throw IllegalArgumentException("There is not enough capacity supplied to support this input. $initialNumbers")


        val numbersReel = generateReelOfNumbers(initialNumbers)
        val extraSpace = capacity - numbersReel.size
        val spaceOnSidesToFill = extraSpace.floorDiv(2)

        reel = mutableListOf<Char>().apply {
            addAll(IntRange(1,spaceOnSidesToFill-1).map { BLANK })
            addAll(numbersReel)
            addAll(IntRange(1,spaceOnSidesToFill-1).map { BLANK })
        }

        reelPosition = spaceOnSidesToFill - 1
    }


    private fun generateReelOfNumbers(numbers: List<Int>): List<Char> = mutableListOf(BLANK)
            .apply {
                numbers.forEach {
                    addAll(IntRange(1, it).map { FILL })
                    add(BLANK)
                }
            }


    fun calculateIntegersOnReel() = reel.countsOfConsecutiveEquality(ignoring = setOf(BLANK))

    fun process(quadruple: Quadruple): TapeProcessResult {
        if (quadruple.startingState != currentState)
            throw TapeExecutionException("$currentState does not match ${quadruple.startingState}")

        return when (quadruple.command) {
            Command.LEFT -> move(
                left = true,
                successfulQuadrupleEndName = quadruple.end
            )
            Command.RIGHT -> move(
                left = false,
                successfulQuadrupleEndName = quadruple.end
            )
            Command.FILL -> write(fill = true, successQuadrupleEnd = quadruple.end)
            Command.BLANK -> write(fill = false, successQuadrupleEnd = quadruple.end)
        }.also { currentState = it.endingState }

    }

    private fun write(fill: Boolean = true, successQuadrupleEnd: String): TapeProcessResult {
        reel[reelPosition] = if (fill) FILL else BLANK
        return TapeProcessResult.WriteSuccess(
            reelPosition,
            State(successQuadrupleEnd, reel[reelPosition])
        )
    }

    private fun move(left: Boolean = true, successfulQuadrupleEndName: String): TapeProcessResult {
        val reelPositionChange = if (left) -1 else 1
        val isValidPositionChange =
            reelPosition + reelPositionChange in 0 until capacity-1

        return if (isValidPositionChange){
            reelPosition += reelPositionChange
            TapeProcessResult.MovementSuccess(
                newPosition = reelPosition,
                State(successfulQuadrupleEndName, reel[reelPosition])
            )
        }

        else TapeProcessResult.MovementFailure(reelPosition, currentState)

    }


    companion object {
        private const val INITIAL_QUADRUPLE_STATE_NAME = "1"
        private const val BLANK = 'B'
        private const val FILL = '1'
    }

}

