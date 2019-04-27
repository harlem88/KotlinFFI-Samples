package org.dronix.kotlin.ffi.samples.libc

import jnr.ffi.LibraryLoader
import jnr.ffi.Memory
import jnr.ffi.Runtime
import java.nio.ByteBuffer
import java.nio.ByteOrder

class LibcSamples {
    private val libc =  LibraryLoader.create(LibC::class.java).load("c")
    private val runtime = Runtime.getRuntime(libc)

    fun timeOfDay(){
        val tv = Timeval(runtime)
        libc.gettimeofday(tv, null)
        println("gettimeofday tv.tv_sec= ${tv.tv_sec.get()} tv.tv_usec=${tv.tv_usec.get()}")
    }

    fun pid(){
        println("pid=${libc.getpid()} ppid=${libc.getppid()}")
    }

    fun login(){
        println("login: ${libc.getlogin()}")
    }

    fun qsortIntArray(){
        val numbers = intArrayOf(2, 1, 3, 66, 1111, 8, 6, 99, 0, 88)
        println("qsort using  IntArray")
        numbers.forEach { print("$it ")}
        println("")

        libc.qsort(numbers, numbers.size, 4, IntCompare())
        numbers.forEach { print("$it ")}
        println("")
    }

    fun qsortNativeMemory(){
        println("sort using native memory")
        val memory = Memory.allocate(Runtime.getRuntime(libc), 8)
        memory.putInt(0, 4)
        memory.putInt(4, 3)

        println("before, memory[0]=" + memory.getInt(0) + " memory[1]=" + memory.getInt(4))
        libc.qsort(memory, 2, 4, IntCompare())
        println("after, memory[0]=" + memory.getInt(0) + " memory[1]=" + memory.getInt(4))
    }

    fun qsortNIOBuffer(){
        println("qsort using NIO buffer")
        val intBuffer = ByteBuffer.allocateDirect(8).order(ByteOrder.nativeOrder()).asIntBuffer()
        intBuffer.put(0, 6)
        intBuffer.put(1, 5) // offset is in units of int elements

        println("before, buffer[0]=" + intBuffer.get(0) + " buffer[1]=" + intBuffer.get(1))
        libc.qsort(intBuffer, 2, 4, IntCompare())
        println("after, buffer[0]=" + intBuffer.get(0) + " buffer[1]=" + intBuffer.get(1))
    }
}