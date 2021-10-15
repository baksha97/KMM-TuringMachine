package com.example.turingmachineapp

import com.example.turingmachineapp.factories.MachineFactory
import com.example.turingmachineapp.models.Tape
import com.example.turingmachineapp.models.TapeProcessResult
import com.example.turingmachineapp.models.TuringMachine
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
