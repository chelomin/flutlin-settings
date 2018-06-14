package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.CardView
import android.view.View
import android.widget.FrameLayout
import com.samsclub.flutlinui.SettingsView
import com.samsclub.flutlinui.base.InitParams
import com.samsclub.flutlinui.base.SettingsItem
import com.samsclub.flutlinui.style.LTRB

/**
 * Created by y0c021m on 5/29/18.
 */
class Card (
        val view: SettingsView,
        private val margin: LTRB? = null,
        private val padding: LTRB? = null,
        visibility: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {
        val cardView = CardView(ip.context)

        val frameLayout = FrameLayout(ip.context)

        frameLayout.addView(view.inflate(ip))
        cardView.addView(frameLayout)

        applyPadding(frameLayout, padding)
        applyMargin(cardView, margin)

        root = cardView

        onBuilt()

        return root
    }
}