package com.yourcast.app.util

import com.google.gson.Gson

object Extensions {

    inline fun <reified T : Any> T.json(): String = Gson().toJson(this, T::class.java)
    inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this,T::class.java)
}