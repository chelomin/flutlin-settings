package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.view.View
import android.widget.FrameLayout
import com.samsclub.flutlinui.SettingsView
import com.samsclub.flutlinui.base.InitParams
import com.samsclub.flutlinui.base.SettingsItem
import com.samsclub.flutlinui.style.LTRB


/**
 * Created by y0c021m on 5/23/18.
 */
class Page(
    private val widget: SettingsItem,
    private val page: SettingsView,
    val visibility: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {

        widget.init(ip)
        root = widget.build()

        root.setOnClickListener { page.run(ip) }

        onBuilt()

        return root
    }
}