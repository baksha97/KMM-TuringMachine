package com.example.turingmachineapp

import com.example.turingmachineapp.factories.MachineFactory
import com.example.turingmachineapp.models.Tape
import com.example.turingmachineapp.models.TapeProcessResult
import com.example.turingmachineapp.models.TuringMachine
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TuringMachineTest {

    @Test
    fun testSubsequentStateWithNext() {
        val x_squared = """
            1,B,R,2
        """.trimIndent()


        val factory = MachineFactory()
        val program = factory.makeProgram(x_squared)
        val tape = factory.makeTape(10)

        val machine = TuringMachine(tape, program)

        assertTrue(machine.hasSubsequentState())
    }

    @Test
    fun testSubsequentStateWithoutNext() {
        val x_squared = """
            1,B,R,2
        """.trimIndent()


        val factory = MachineFactory()
        val program = factory.makeProgram(x_squared)
        val tape = factory.makeTape(10)

        val machine = TuringMachine(tape, program)
        machine.executeSubsequentQuadruple()
        assertFalse(machine.hasSubsequentState())
    }
}
