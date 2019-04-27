package org.dronix.kotlin.ffi.samples.libc

import jnr.ffi.Pointer
import java.nio.Buffer

interface LibC {
    fun gettimeofday(tv: Timeval, unused: Pointer?): Int
    fun getpid() : Long
    fun getppid() : Long
    fun getlogin(): String
    fun qsort(data : IntArray, count: Int, width: Int, compare: Compare)
    fun qsort(data : Pointer, count: Int, width: Int, compare: Compare)
    fun qsort(data : Buffer, count: Int, width: Int, compare: Compare)
}