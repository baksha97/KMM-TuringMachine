package com.example.turingmachineapp

import com.example.turingmachineapp.models.Command
import com.example.turingmachineapp.models.Quadruple
import com.example.turingmachineapp.models.State

open class TapeExecutionException(message: String? = null) : Exception(message)

data class Tape(val initialSize: Int = 9) {
    private var reelPosition = initialSize.floorDiv(2)

    var currentState: State = State("1", BLANK)
    var reel = mutableMapOf<Int, Char>()

    private var left = 0
    private var right = initialSize

    private var _size = right - left
    val size = _size

    init {
        IntRange(0, initialSize).forEach {
            reel[it] = BLANK
        }
    }

    fun execute(quadruple: Quadruple) {
        if(quadruple.startingState != currentState)
            throw TapeExecutionException("$currentState does not match ${quadruple.startingState}")

        when(quadruple.command){
            Command.LEFT -> print("")
            Command.RIGHT -> print("")
            Command.FILL -> print("")
            Command.BLANK -> print("")
        }

    }

    //TODO: Clip excess BLANKs if moving left when there on right pointer and has excess BLANKs
    private fun moveLeft() {
        reelPosition--
        if (reelPosition < left) {
            left--
            reel[left] = BLANK
        }
    }

    //TODO: Clip excess BLANKs if moving right when there on left pointer and has excess BLANKs
    private fun moveRight() {
        reelPosition++
        if (reelPosition > right) {
            right++
            reel[right] = BLANK
        }
    }

    companion object {
        private val BLANK = 'B'
        private val FILL = '1'
    }

}


//
//import java.lang.IllegalArgumentException
//import java.util.ArrayList
//
//
//class Tape {
//    private var cells: ObservableList<Char>? = null
//    var pos = 0
//        private set
//    var currentState: State? = null
//        private set
//
//    private constructor() {
//
//    }
//    constructor(vararg inputs: Int) {
//        initialize()
//        for (input: Int in inputs) {
//            cells.addAll(intInputAsFill(input))
//            cells.add(BLANK)
//        }
//    }
//
//    constructor(input: String) {
//        initialize()
//        var charCheck = 0
//        while (input[charCheck] == BLANK) charCheck++
//        for (i in charCheck until input.length) {
//            val cur = input[i]
//            if (cur == ' ' || cur == ',') continue
//            if (cur != FILL && cur != BLANK) throw IllegalArgumentException(
//                "Tape input is not valid for: $cur"
//            )
//            cells.add(cur)
//        }
//        cells.add(BLANK)
//    }
//
//    fun getCells(): ObservableList<Char>? {
//        return cells
//    }
//
//    private fun initialize() {
//        cells = FXCollections.observableArrayList()
//        cells.add(BLANK)
//        pos = 0
//        currentState = State("1", BLANK)
//    }
//
//    private fun intInputAsFill(x: Int): List<Char> {
//        val list = ArrayList<Char>(x)
//        for (i in 0 until x) {
//            list.add(FILL)
//        }
//        return list
//    }
//
//    fun execute(q: Quadruple) {
//        if (!currentState!!.equals(q.startingState)) throw IllegalArgumentException(
//            "This function cannot be executed because it does not match state."
//                    + "\nState: " + currentState
//                    + "\nQuadruple: " + q.startingState
//        )
//        when (q.command) {
//            Command.LEFT -> moveLeft()
//            Command.RIGHT -> moveRight()
//            Command.FILL -> cells.set(pos, FILL)
//            Command.BLANK -> cells.set(pos, BLANK)
//        }
//        currentState = State(q.end, cells.get(pos))
//    }
//
//    private fun moveLeft() {
//        pos--
//        if (pos <= 0) {
//            cells.add(0, BLANK)
//            pos++
//        }
//    }
//
//    private fun moveRight() {
//        pos++
//        if (pos == cells.size() - 1) {
//            cells.add(BLANK)
//        }
//    }
//
//    override fun toString(): String {
//        val s: String = cells.toString()
//            .replace("[", "")
//            .replace("]", "")
//            .replace(",", "")
//            .replace(" ", "")
//        return s.substring(0, pos) + " || " + s[pos] + " || " + s.substring(pos + 1)
//    }
//
//    fun currentNumbersOnTape(): List<Int> {
//        val res = ArrayList<Int>()
//        var current = 0
//        for (cell: Char in cells) {
//            if (cell == FILL) {
//                current++
//            } else if (current != 0 && cell == BLANK) {
//                res.add(current)
//                current = 0
//            }
//        }
//        if (current != 0) res.add(current)
//        return res
//    }
//
//    companion object {
//        private val BLANK = 'B'
//        private val FILL = '1'
//    }
//}