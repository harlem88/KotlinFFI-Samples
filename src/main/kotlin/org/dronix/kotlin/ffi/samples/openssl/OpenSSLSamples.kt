package org.dronix.kotlin.ffi.samples.openssl

import jnr.ffi.LibraryLoader
import jnr.ffi.Memory
import jnr.ffi.Pointer
import jnr.ffi.Runtime

const val BIO_CTRL_INFO = 3
const val RSA_F4 = 65537L

class OpenSSLSamples{

    fun generateKey(bits: Int){
        val openssl = LibraryLoader.create(OpenSSL::class.java).load("ssl")
        val bne = openssl.BN_new()

        if (openssl.BN_set_word(bne, RSA_F4) != 1) {
            println("error: bne")
            return
        }

        val rsa = openssl.RSA_new()

        if (openssl.RSA_generate_key_ex(rsa, bits, bne, null) != 1) {
            println("openssl error")
            return
        }

        println("openssl key generated successful")

        val pBIOPub = openssl.BIO_new(openssl.BIO_s_mem())
        if (openssl.PEM_write_bio_RSAPublicKey(pBIOPub, rsa) != 1) {
            println("write pub bio error")
            return
        }

        val pBIOPriv = openssl.BIO_new(openssl.BIO_s_mem())
        if (openssl.PEM_write_bio_RSAPrivateKey(pBIOPriv, rsa, null, null, 0, null, null) != 1) {
            println("write priv bio error")
            return
        }

        getKey(openssl, pBIOPub)
        getKey(openssl, pBIOPriv)

        openssl.BIO_free_all(pBIOPub)
        openssl.BIO_free_all(pBIOPriv)
        openssl.RSA_free(rsa)
        openssl.BN_free(bne)
    }

    private fun getKey(openssl: OpenSSL, pKeyPointer: Pointer): String{
        val ret = openssl.BIO_ctrl(pKeyPointer, BIO_CTRL_INFO, 0, null)
        val ppData = Memory.allocate(Runtime.getRuntime(openssl), ret.toInt())
        println("BIO_ctrl total amount of data available $ret")
        openssl.BIO_ctrl(pKeyPointer, BIO_CTRL_INFO, 0, ppData)
        val pData = ppData.getPointer(0)
        return pData.getString(0)
    }

    private fun writeFile(openssl: OpenSSL, rsa: Pointer){
        val publicFileName = "public.pem"
        val bpPublic = openssl.BIO_new_file(publicFileName, "w+")
        if( openssl.PEM_write_bio_RSAPublicKey(bpPublic, rsa) != 1){
            println("write $publicFileName file error")
            return
        }

        val privateFileName = "private.pem"
        val bpPrivate = openssl.BIO_new_file(privateFileName, "w+")
        if( openssl.PEM_write_bio_RSAPrivateKey(bpPrivate, rsa, null, null, 0, null, null) != 1) {
            println("write $privateFileName file error")
            return
        }

    }
}