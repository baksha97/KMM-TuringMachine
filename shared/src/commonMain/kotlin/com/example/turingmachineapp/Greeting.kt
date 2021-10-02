package com.example.turingmachineapp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}