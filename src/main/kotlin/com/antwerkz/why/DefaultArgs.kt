package com.antwerkz.why

object Overloads {
    @JvmStatic
    @JvmOverloads
    fun bar(name: String = "Bob", age: Int = 42) {}
}