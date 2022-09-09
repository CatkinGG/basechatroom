package com.dabenxiang.jys.basechatroom.widget

import android.annotation.SuppressLint
import android.provider.Settings
import com.dabenxiang.jys.basechatroom.App

object AppUtils {

    @SuppressLint("HardwareIds")
    fun getAndroidID(): String {
        return Settings.Secure.getString(
            App.applicationContext().contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }
}