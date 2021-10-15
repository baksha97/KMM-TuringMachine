package com.baksha97.turingmachineapp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}