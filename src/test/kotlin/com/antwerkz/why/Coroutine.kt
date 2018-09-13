package com.antwerkz.why

import kotlinx.coroutines.experimental.Runnable
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.cancelChildren
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select
import org.testng.annotations.Test
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit.SECONDS
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.coroutineContext


@Test
class Coroutine {
    fun lightWeight() {
        runBlocking {
            val num = AtomicInteger()
            val jobs = List(100_000) {
                launch {
                    work(num)
                }
            }
            jobs.forEach { it.join() }

            println()
            println("Done:  ${num.get()}")
        }
    }

    private suspend fun work(num: AtomicInteger) {
        delay(1000L)
        num.incrementAndGet()
    }

    fun lightWeight_threads() {
        runBlocking {
            val queue = SynchronousQueue<Runnable>()
            val pool = ThreadPoolExecutor(1000, 10000, 60L, SECONDS, queue)
            println(pool.prestartAllCoreThreads().toString() + " threads started")
            val num = AtomicInteger()
            repeat(100_000) {
                if (it % 10000 == 0) println("queueing item #$it")
                queue.offer(Runnable {
                    Thread.sleep(1000L)
                    num.incrementAndGet()
                }, 60, SECONDS)
            }
            println("Threads queued")
            pool.awaitTermination(30, SECONDS)

            println()
            println("Done:  ${num.get()}")
        }
    }

    fun withAsync() {
        runBlocking {
            val job = launch {
                delay(2000)
                println("I was launched")
                "job"
            }
            val deferred = async {
                delay(2000)
                println("I am async")
                "job"
            }

            println(job.join())
            println(deferred.await())
        }
    }


    fun fizz(context: CoroutineContext) = produce(context) {
        while (true) { // sends "Fizz" every 300 ms
            delay(300)
            send("Fizz")
        }
    }

    fun buzz(context: CoroutineContext) = produce(context) {
        while (true) { // sends "Buzz!" every 500 ms
            delay(500)
            send("Buzz!")
        }
    }

    suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
        select<Unit> {
            fizz.onReceive { value ->
                println("fizz -> '$value'")
            }
            buzz.onReceive { value ->
                println("buzz -> '$value'")
            }
        }
    }

    fun fizzbuzz() {
        runBlocking {
            val fizz = fizz(coroutineContext)
            val buzz = buzz(coroutineContext)
            repeat(10) {
                selectFizzBuzz(fizz, buzz)
            }
            coroutineContext.cancelChildren()
        }
    }
}
