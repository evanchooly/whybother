package com.antwerkz.javaone

import org.testng.annotations.Test
import java.time.LocalDateTime

class DelegationExamplesTest {
    @Test
    fun user() {
        val user = User("Pam Beesly")

        user.name = "Pam Halpert"
        println(LocalDateTime.now())
        println("user.created = ${user.created}")

        try {
            println("user.age = ${user.age}")
        } catch (e: Exception) {
            println(e.message)
        }

        user.age = 29;
        println("user.age = ${user.age}")
    }
}