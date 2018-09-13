package com.antwerkz.why

import org.testng.annotations.Test
import java.lang.Thread.sleep

@Test
class ListogramTest {
    fun time() {
        val list = Listogram()
        val map = mutableMapOf("a" to "b")
        map["a"] = "c"
        add(list, listOf("first", "second"))
        add(list, listOf("third", "fourth", "fifth"))
        list.remove("fourth")
        list.add("sixth")
        list.remove("second")
        sleep(1000)
        (1..7).forEach {
            list.add(it.toString())
            sleep(1000)
        }
        (2..5).forEach {
            list.remove(it.toString())
        }

        list.histogram()
    }

    private fun add(list: MutableList<String>, adds: List<String>) {
        adds.forEach { list.add(it) }
        sleep(1000)
    }


    @Test
    fun data() {
        println(Person("James", 12))
    }

}

data class Person(val name: String, val age: Int)