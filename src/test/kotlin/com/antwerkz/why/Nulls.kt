package com.antwerkz.why

import org.junit.Test

class Nulls {

    @Test
    fun nullChecks() {
        var s : String? = maybe()

        println(s ?: "never print a null!")

        if(s != null) {
            val newS = s
            println("you made it!")
        }

        s?.let {
            val newS = it
            print("let it be")
        }

    }


    fun maybe(): String? {
        fun String.toTitleCase() : String {
            return this
        }

        return "how about now".toTitleCase()
    }


}
