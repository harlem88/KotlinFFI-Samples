package org.dronix.kotlin.ffi.samples.libc

import jnr.ffi.Struct
import jnr.ffi.Runtime

class Timeval(runtime: Runtime): Struct(runtime) {
    val tv_sec: time_t = super.time_t()
    val tv_usec: SignedLong = super.SignedLong()
}