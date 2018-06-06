package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.samsclub.flutlinui.SettingsView
import com.samsclub.flutlinui.base.InitParams
import com.samsclub.flutlinui.base.SettingsItem

/**
 * Created by y0c021m on 5/29/18.
 */
class Box(
        private val child: SettingsItem,
        private val boxParams: BoxParams? = null,
        visibility: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {
        val box = RelativeLayout(context)

        child.init(InitParams(context,inflater, lifecycleOwner))
        box.addView(child.build())

        if (boxParams != null) {
            val lpVg: ViewGroup.LayoutParams = box.layoutParams  ?: RelativeLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            val lp = lpVg as RelativeLayout.LayoutParams

            with(boxParams) {
                lp.setMargins(marginStart, marginTop, marginEnd, marginBottom)
                box.layoutParams = lp
                box.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
            }
        }

        root = box

        onBuilt()

        return root
    }
}

data class BoxParams(
        val marginStart: Int = 0,
        val marginEnd: Int = 0,
        val marginTop: Int = 0,
        val marginBottom: Int = 0,
        val paddingStart: Int = 0,
        val paddingEnd: Int = 0,
        val paddingTop: Int = 0,
        val paddingBottom: Int = 0
)