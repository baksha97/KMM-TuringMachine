package com.baksha97.turingmachineapp.models

import com.baksha97.turingmachineapp.utils.countsOfConsecutiveEquality

open class TapeExecutionException(message: String? = null) : Exception(message)

sealed class TapeProcessResult(val endingState: String, val endingValue: Int) {
    class MovementSuccess(val newPosition: Int, endingState: String, endingValue: Int) :
        TapeProcessResult(endingState, endingValue)

    class WriteSuccess(val index: Int, endingState: String, endingValue: Int) :
        TapeProcessResult(endingState, endingValue)

    class MovementFailure(val index: Int, endingState: String, endingValue: Int) :
        TapeProcessResult(endingState, endingValue)
}

data class Tape internal constructor(private val capacity: Int, val initialNumbers: List<Int> = listOf(1)) {

    internal var currentState: State = State(INITIAL_QUADRUPLE_STATE_NAME, BLANK)
    var reelPosition: Int

    var _reel: MutableList<Int>
    val reel: List<Int>
        get() = _reel

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

        _reel = mutableListOf<Int>().apply {
            addAll(IntRange(1, spaceOnSidesToFill).map { BLANK })
            addAll(numbersReel)
            addAll(IntRange(1, spaceOnSidesToFill).map { BLANK })
        }

        reelPosition = spaceOnSidesToFill
    }


    fun calculateIntegersOnReel() = _reel.countsOfConsecutiveEquality(ignoring = setOf(BLANK))

    internal fun process(quadruple: Quadruple): TapeProcessResult {
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
        }.also { currentState = State(it.endingState, it.endingValue) }

    }

    private fun write(fill: Boolean = true, successfulQuadrupleEnd: String): TapeProcessResult {
        _reel[reelPosition] = if (fill) FILL else BLANK
        return TapeProcessResult.WriteSuccess(
            reelPosition,
            successfulQuadrupleEnd,
            _reel[reelPosition]
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
                successfulQuadrupleEndName, _reel[reelPosition]
            )
        } else TapeProcessResult.MovementFailure(
            reelPosition,
            currentState.name,
            currentState.value
        )
    }

    override fun toString(): String {
        return _reel.toString()
    }

    companion object {
        const val INITIAL_QUADRUPLE_STATE_NAME = "1"
        const val BLANK = 0
        const val FILL = 1
    }

}

