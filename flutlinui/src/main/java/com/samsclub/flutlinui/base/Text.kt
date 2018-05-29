package com.samsclub.flutlinui.base

import android.arch.lifecycle.MutableLiveData
import android.content.Context

/**
 * Created by y0c021m on 5/23/18.
 */
sealed class Text {
    companion object {
        fun from(resId: Int): Text = TextFromResource(resId)
        fun from(str: String): Text = TextFromString(str)
        fun from(ldStr: MutableLiveData<String>): Text = TextFromLiveDataString(ldStr)
    }

    fun get(context: Context): String = when (this) {
        is TextFromResource -> context.resources.getString(resId)
        is TextFromString -> str
        is TextFromLiveDataString -> ldStr.value ?: ""
    }
}

data class TextFromResource(val resId: Int) : Text()
data class TextFromString(val str: String) : Text()
data class TextFromLiveDataString(val ldStr: MutableLiveData<String>) : Text()
