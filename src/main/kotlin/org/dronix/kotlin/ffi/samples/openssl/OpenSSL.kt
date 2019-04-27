package org.dronix.kotlin.ffi.samples.openssl

import jnr.ffi.Pointer
import java.nio.Buffer

interface OpenSSL {
    fun RSA_generate_key_ex(rsa: Pointer, bits: Int, e_value: Pointer?, cb: Pointer?): Int
    fun RSA_new(): Pointer
    fun BN_new(): Pointer
    fun BN_set_word(bne: Pointer, e: Long): Int
    fun BIO_new_file(fileName: String, mode: String): Pointer
    fun BIO_new(type: Pointer): Pointer
    fun BIO_s_mem(): Pointer
    fun PEM_write_RSAPublicKey(file: Pointer, rsa: Pointer): Int
    fun PEM_write_bio_RSAPublicKey(bio: Pointer, rsa: Pointer): Int
    fun PEM_write_RSAPrivateKey(file: Pointer, rsa: Pointer?, enc: Pointer?, kstr: Pointer?, klen: Int, cb: Pointer?, u: Pointer?): Int
    fun PEM_write_bio_RSAPrivateKey(bio: Pointer, rsa: Pointer?, enc: Pointer?, kstr: Pointer?, klen: Int, cb: Pointer?, u: Pointer?): Int
    fun BIO_free_all(file: Pointer)
    fun BIO_ctrl(pBio: Pointer, cmd: Int, lArg: Long, pArg: Pointer?): Long
    fun BIO_get_mem_data(pBio: Pointer, pp: Buffer)
    fun BUF_MEM_new(): Pointer
    fun RSA_free(rsa: Pointer)
    fun BN_free(bne: Pointer)
}