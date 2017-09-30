package com.antwerkz.javaone

import java.lang.Thread.sleep
import java.time.LocalDateTime
import kotlin.properties.Delegates

class User(name: String) {
    var name: String by Delegates.observable(name) { property, old, new ->
        println("$property has changed from '$old' to '$new'")
    }

    var age: Int by Delegates.notNull()

    val created: LocalDateTime by lazy {
        sleep(5000)
        LocalDateTime.now()
    }
}