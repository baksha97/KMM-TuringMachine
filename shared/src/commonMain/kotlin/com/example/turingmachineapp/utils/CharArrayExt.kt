package com.example.turingmachineapp.utils


fun <T> MutableList<T>.groupByConsecutiveEquality(ignoring: Set<T> = setOf()): List<MutableList<T>> =
    fold(mutableListOf<MutableList<T>>()) { acc, ch ->
        acc.apply {
            if (isEmpty() || last().last() != ch)
                add(mutableListOf(ch))
            else
                last().add(ch)
        }
    }.filter { !ignoring.contains(it.first()) }

fun <T> MutableList<T>.countOfConsecutiveEquality(ignoring: Set<T> = setOf()): List<Int> =
    this.groupByConsecutiveEquality(ignoring).map { it.size }