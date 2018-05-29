package com.samsclub.flutlinui.base

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup

/**
 * Created by y0c021m on 5/23/18.
 */
abstract class SettingsItem(
        private val visibility: MutableLiveData<Boolean>? = null) {
    lateinit var context: Context
    lateinit var inflater: LayoutInflater
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var root: View

    val visibilityObserver = Observer<Boolean> { visible -> root.visibility = if (visible != null && visible) VISIBLE else GONE }

    abstract fun build(): View

    fun init(params: InitParams) {
        context = params.context
        inflater = params.inflater
        lifecycleOwner = params.lifecycleOwner
    }

    fun onBuilt() {
        if (visibility != null) {
            visibility.observe(lifecycleOwner, visibilityObserver)
        }
    }
}

data class InitParams(val context: Context, val inflater: LayoutInflater, val lifecycleOwner: LifecycleOwner)