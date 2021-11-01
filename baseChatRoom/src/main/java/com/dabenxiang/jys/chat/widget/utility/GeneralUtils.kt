package com.dabenxiang.jys.chat.widget.utility

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object GeneralUtils {
    @SuppressLint("ConstantLocale")
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    fun convertTimestampToString(timestamp: Long, pattern: SimpleDateFormat = sdf): String {
            if (timestamp == 0L) return ""
            val calendar = Calendar.getInstance()
            calendar.time = Date(timestamp * 1000)
            return pattern.format(calendar.time)
        }

    fun decodeBase64Image(encode: String): ByteArray? {
        return when {
            encode.contains(";base64,") -> {
                CryptUtils.decodeBase64ToByteArray(encode.split(",")[1])
            }
            else -> null
        }
    }
}
