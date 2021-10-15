package com.baksha97.turingmachineapp

import com.baksha97.turingmachineapp.models.factories.MachineFactory
import com.baksha97.turingmachineapp.models.TuringMachine
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TuringMachineTest {

    @Test
    fun testSubsequentStateWithNext() {
        val sample = """
            1,B,R,2
        """.trimIndent()


        val factory = MachineFactory()
        val program = factory.makeProgram(sample)
        val tape = factory.makeTape(10)

        val machine = TuringMachine("test", tape, program)

        assertTrue(machine.hasNextQuadruple())
    }

    @Test
    fun testSubsequentStateWithoutNext() {
        val x_squared = """
            1,B,R,2
        """.trimIndent()


        val factory = MachineFactory()
        val program = factory.makeProgram(x_squared)
        val tape = factory.makeTape(10)

        val machine = TuringMachine("test", tape, program)
        machine.executeSubsequentQuadruple()
        assertFalse(machine.hasNextQuadruple())
    }
}
