package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.view.View
import android.widget.FrameLayout
import com.samsclub.flutlinui.base.SettingsItem
import com.samsclub.flutlinui.style.LTRB


/**
 * Created by y0c021m on 5/23/18.
 */
class Divider(
    private val color: MutableLiveData<Int>,
    private val padding: LTRB? = null,
    private val height: Int,
    val visibility: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {

        val fl = FrameLayout(context)
        color.observe(lifecycleOwner, Observer<Int> { it -> if (it != null) root.setBackgroundColor(it) })

        applyMargin(fl, padding, height)

        root = fl
        applyPadding(root, padding)

        onBuilt()

        return root
    }
}