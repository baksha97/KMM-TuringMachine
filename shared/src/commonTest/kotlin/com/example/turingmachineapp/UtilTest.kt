package com.example.turingmachineapp

import com.example.turingmachineapp.utils.countOfConsecutiveEquality
import com.example.turingmachineapp.utils.groupByConsecutiveEquality
import kotlin.test.Test
import kotlin.test.assertContentEquals

class UtilTest {

    @Test
    fun testGroupingByConsecutiveEquality() {
        val reel = mutableListOf(
            '0',
            '0',
            '1',
            '1',
            '1',
            '0',
            '1',
            '1',
            '0',
            '0',
            '0',
            '0',
            '0',
            '1'
        )
        val result: List<List<Char>> = reel.groupByConsecutiveEquality()

        println(result)
        assertContentEquals(
            listOf(
                listOf('0', '0'),
                listOf('1', '1', '1'),
                listOf('0'),
                listOf('1', '1'),
                listOf('0', '0', '0', '0', '0'),
                listOf('1')
            ), result
        )
    }

    @Test
    fun testGroupingByConsecutiveEqualityWithIgnoring() {
        val reel = mutableListOf(
            '0',
            '0',
            '1',
            '1',
            '1',
            '0',
            '1',
            '1',
            '0',
            '0',
            '0',
            '0',
            '0',
            '1'
        )
        val result: List<List<Char>> = reel.groupByConsecutiveEquality(setOf('0'))

        println(result)
        assertContentEquals(
            listOf(
                listOf('1', '1', '1'),
                listOf('1', '1'),
                listOf('1')
            ), result
        )
    }

    @Test
    fun testGroupingByConsecutiveEqualityWithIgnoringCount() {
        val reel = mutableListOf(
            '0',
            '0',
            '1',
            '1',
            '1',
            '0',
            '1',
            '1',
            '0',
            '0',
            '0',
            '0',
            '0',
            '1'
        )
        val result: List<Int> = reel.countOfConsecutiveEquality(setOf('0'))

        println(result)
        assertContentEquals(
            listOf(3, 2, 1), result
        )
    }
}