package com.antwerkz.why

import java.time.LocalTime
import java.time.format.DateTimeFormatter.ofPattern

class Listogram(private val list: MutableList<String> = mutableListOf()) : MutableList<String> by list {
    val history = mutableListOf<Pair<LocalTime, Int>>()
    override fun add(element: String): Boolean {
        val add = list.add(element)
        history.add(LocalTime.now() to size)
        return add
    }

    override fun remove(element: String): Boolean {
        val remove = list.remove(element)
        history.add(LocalTime.now() to size)
        return remove
    }

    fun histogram() {

        history.groupBy { it.first.toSecondOfDay() }
                .map { it.value.last() }
                .map { it.first.format(ofPattern("hh:mm:ss")) to (0..it.second).fold("", { acc, i -> acc + "*" }) }
                .forEach {
                    println("${it.first}: ${it.second}")
                }
    }

}


