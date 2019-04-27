package org.dronix.kotlin.ffi.samples

import org.dronix.kotlin.ffi.samples.libc.LibcSamples
import org.dronix.kotlin.ffi.samples.openssl.OpenSSLSamples

fun main(args: Array<String>) {
    println("== LibC Samples ==")
    val libcSamples = LibcSamples()
    libcSamples.timeOfDay()
    libcSamples.pid()
    libcSamples.login()
    libcSamples.qsortIntArray()
    libcSamples.qsortNativeMemory()
    libcSamples.qsortNIOBuffer()

    println("\n== OpenSSL RSA PRIV-PUB ==")
    val openSSLSamples = OpenSSLSamples()
    openSSLSamples.generateKey(2048)
}