package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.CardView
import android.view.View
import android.widget.FrameLayout
import com.samsclub.flutlinui.SettingsView
import com.samsclub.flutlinui.base.InitParams
import com.samsclub.flutlinui.base.SettingsItem

/**
 * Created by y0c021m on 5/29/18.
 */
class Card (
        val view: SettingsView,
        private val boxParams: BoxParams? = null,
        visibility: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {
        val cardView = CardView(context)

        val frameLayout = FrameLayout(context)

        frameLayout.addView(view.inflate(InitParams(context, inflater, lifecycleOwner)))
        cardView.addView(frameLayout)

        root = cardView

        applyBoxParams(frameLayout, boxParams)
        onBuilt()

        return root
    }
}