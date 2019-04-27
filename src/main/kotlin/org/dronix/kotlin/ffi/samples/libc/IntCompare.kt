package org.dronix.kotlin.ffi.samples.libc

import jnr.ffi.Pointer

class IntCompare: Compare {
    override fun compare(p1: Pointer, p2: Pointer): Int {
        val i1 = p1.getInt(0)
        val i2 = p2.getInt(0)

        return when {
            i1 < i2 -> - 1
            i1 > i2 -> 1
            else -> 0
        }
    }
}