package org.dronix.kotlin.ffi.samples.libc

import jnr.ffi.Pointer
import jnr.ffi.annotations.Delegate

interface Compare{
    @Delegate
    fun compare(p1: Pointer, p2: Pointer): Int
}
