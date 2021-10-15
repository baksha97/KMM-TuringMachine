package com.baksha97.turingmachineapp

import com.baksha97.turingmachineapp.factories.MachineFactory
import com.baksha97.turingmachineapp.models.Tape
import com.baksha97.turingmachineapp.models.TapeProcessResult
import com.baksha97.turingmachineapp.models.TuringMachine
import kotlin.math.pow
import kotlin.test.*

class TapeTest {
    private val factory = MachineFactory()

    @Test
    fun testReelStartsOnBlanks() {
        val tape = factory.makeTape(10)
        assertEquals(tape.reel[tape.reelPosition], Tape.BLANK)
    }

    @Test
    fun testReelNextToFill() {
        val tape = factory.makeTape(10)
        assertEquals(tape.reel[tape.reelPosition+1], Tape.FILL)
    }

    @Test
    fun testStartingReelAndPositionMatchState() {
        val tape = factory.makeTape(10)
        assertEquals(tape.reel[tape.reelPosition], tape.currentState.value)
        assertEquals(Tape.INITIAL_QUADRUPLE_STATE_NAME, tape.currentState.name)
    }
}
