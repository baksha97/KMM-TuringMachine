package com.example.turingmachineapp

import java.lang.StringBuilder

class Program(programData: String) {
    private val quadruples: List<Quadruple>
    private val quadrupleStates: Map<State, Quadruple>

    private fun size(): Int {
        return quadruples.size
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Quadruple Count: ").append(size()).append('\n')
        for (q in quadruples) {
            sb.append(q.toString()).append('\n')
        }
        return sb.toString()
    }

    init {
        val lines = programData
            .lineSequence()

        quadruples = lines
            .map {
                Quadruple(it)
            }.toList()

        val groupedQuadruple = quadruples
            .groupBy { it.startingState }
            .takeIf { entries -> entries.all { it.value.size == 1 } } ?: throw UnsupportedOperationException()

        quadrupleStates = groupedQuadruple.mapValues { it.value.first() }

    }
}