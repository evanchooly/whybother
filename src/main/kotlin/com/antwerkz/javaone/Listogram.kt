package com.antwerkz.javaone

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter.ofPattern

class Listogram(private val list: MutableList<String> = mutableListOf()): MutableList<String> by list {
    val history = mutableListOf<Pair<LocalDateTime, Int>>()
    override fun add(element: String): Boolean {
        val add = list.add(element)
        history.add(LocalDateTime.now() to size)
        return add
    }

    override fun remove(element: String): Boolean {
        val remove = list.remove(element)
        history.add(LocalDateTime.now() to size)
        return remove
    }

    fun histogram() {
        val map = history.groupBy { it.first.toEpochSecond(ZoneOffset.UTC) }
                .map { it.value.last() }
                .map { it.first.format(ofPattern("hh:mm:ss")) to (0..it.second).fold("", { acc, i -> acc + "*" }) }

        map.forEach {
            println("${it.first}: ${it.second}")
        }
    }

}
