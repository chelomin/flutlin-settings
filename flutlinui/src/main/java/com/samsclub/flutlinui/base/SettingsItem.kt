package com.samsclub.flutlinui.base

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.samsclub.flutlinui.style.LTRB
import com.samsclub.flutlinui.widget.BoxParams

/**
 * Created by y0c021m on 5/23/18.
 */
abstract class SettingsItem(
        private val visibility: MutableLiveData<Boolean>? = null) {
    lateinit var context: Context
    lateinit var inflater: LayoutInflater
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var root: View

    private val visibilityObserver = Observer<Boolean> { visible ->
        root.visibility = if (visible != null && visible) VISIBLE else GONE
    }

    abstract fun build(): View

    fun init(params: InitParams) {
        context = params.context
        inflater = params.inflater
        lifecycleOwner = params.lifecycleOwner
    }

    fun onBuilt() {
        visibility?.observe(lifecycleOwner, visibilityObserver)
    }

    fun applyBoxParams(vg: ViewGroup, boxParams: BoxParams?) {
        if (boxParams != null) {
            val lp: ViewGroup.LayoutParams = vg.layoutParams
                    ?: ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val mlp = lp as ViewGroup.MarginLayoutParams

            with(boxParams) {
                mlp.setMargins(margin.left, margin.top, margin.right, margin.bottom)
                vg.layoutParams = mlp
                vg.setPadding(padding.left, padding.top, padding.right, padding.bottom)
            }
        }
    }

    fun applyMargin(vg: ViewGroup, margin: LTRB?) {
        if (margin != null) {
            val lp: ViewGroup.LayoutParams = vg.layoutParams
                    ?: ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val mlp = lp as ViewGroup.MarginLayoutParams

            with(margin) {
                mlp.setMargins(left, top, right, bottom)
                vg.layoutParams = mlp
            }
        }
    }

    fun applyPadding(view: View, padding: LTRB?) {
        if (padding != null) {
            with(padding) {
                view.setPadding(left, top, right, bottom)
            }
        }
    }
}

data class InitParams(val context: Context, val inflater: LayoutInflater, val lifecycleOwner: LifecycleOwner)


val density by lazy {
    Resources.getSystem().displayMetrics.density
}

fun dp(dp: Int): Int {
    val retVal = (dp * density).toInt()
    Log.d("yury", "dp($dp) -> $retVal")
    return retVal
}

/**
 * Creates MutableLiveData from value
 */
fun <T> mld(value: T): MutableLiveData<T> {
    val liveData = MutableLiveData<T>()
    liveData.value = value

    return liveData
}