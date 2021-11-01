package com.dabenxiang.jys.chat.widget.utility

import android.util.Base64


object CryptUtils {
    fun decodeBase64ToByteArray(encode: String): ByteArray {
        return Base64.decode(encode, Base64.DEFAULT)
    }
}