package com.antwerkz.javaone

import org.testng.annotations.Test
import java.lang.Thread.sleep

@Test
class ListogramTest {
    fun time() {
        val list = Listogram()

        add(list, listOf("first", "second"))
        add(list, listOf("third", "fourth", "fifth"))
        list.remove("fourth")
        list.add("sixth")
        list.remove("second")
        sleep(1000)
        add(list, (1..7).map { it.toString() })
        (2..5).forEach {
            list.remove(it.toString())
        }

        list.histogram()
    }

    private fun add(list: MutableList<String>, adds: List<String>) {
        adds.forEach { list.add(it) }
        sleep(1000)
    }

}