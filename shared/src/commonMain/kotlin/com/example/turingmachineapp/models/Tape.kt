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

data class Tape(private val capacity: Int, val initialNumbers: List<Int> = listOf(1)) {

    var currentState: State = State(INITIAL_QUADRUPLE_STATE_NAME, BLANK)
    var reelPosition: Int
    val reel: MutableList<Int> //TODO: Encapsulate

    init {
        val numbersReel = mutableListOf(BLANK)
            .apply {
                initialNumbers.forEach {
                    addAll(IntRange(1, it).map { FILL })
                    add(BLANK)
                }
            }
        val extraSpace = capacity - numbersReel.size
        val spaceOnSidesToFill = extraSpace.floorDiv(2)

        reel = mutableListOf<Int>().apply {
            addAll(IntRange(1, spaceOnSidesToFill).map { BLANK })
            addAll(numbersReel)
            addAll(IntRange(1, spaceOnSidesToFill).map { BLANK })
        }

        reelPosition = spaceOnSidesToFill
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
            Command.FILL -> write(fill = true, successfulQuadrupleEnd = quadruple.end)
            Command.BLANK -> write(fill = false, successfulQuadrupleEnd = quadruple.end)
        }.also { currentState = it.endingState }

    }

    private fun write(fill: Boolean = true, successfulQuadrupleEnd: String): TapeProcessResult {
        reel[reelPosition] = if (fill) FILL else BLANK
        return TapeProcessResult.WriteSuccess(
            reelPosition,
            State(successfulQuadrupleEnd, reel[reelPosition])
        )
    }

    private fun move(left: Boolean = true, successfulQuadrupleEndName: String): TapeProcessResult {
        val reelPositionChange = if (left) -1 else 1
        val isValidPositionChange =
            reelPosition + reelPositionChange in 0 until capacity - 1

        return if (isValidPositionChange) {
            reelPosition += reelPositionChange
            TapeProcessResult.MovementSuccess(
                newPosition = reelPosition,
                endingState = State(successfulQuadrupleEndName, reel[reelPosition])
            )
        } else TapeProcessResult.MovementFailure(reelPosition, currentState)
    }

    override fun toString(): String {
        return reel.toString()
    }

    companion object {
        const val INITIAL_QUADRUPLE_STATE_NAME = "1"
        const val BLANK = 0
        const val FILL = 1
    }

}

