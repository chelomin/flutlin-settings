package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RelativeLayout
import com.samsclub.flutlinui.base.InitParams
import com.samsclub.flutlinui.base.SettingsItem
import com.samsclub.flutlinui.style.LTRB

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
                lp.setMargins(margin.left, margin.top, margin.right, margin.bottom)
                box.layoutParams = lp
                box.setPadding(padding.left, padding.top, padding.right, padding.bottom)
            }
        }

        root = box

        onBuilt()

        return root
    }
}

data class BoxParams(
        val margin: LTRB,
        val padding: LTRB
)