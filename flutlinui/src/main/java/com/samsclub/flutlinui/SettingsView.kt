package com.samsclub.flutlinui

import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import com.samsclub.flutlinui.base.InitParams
import com.samsclub.flutlinui.base.SettingsItem
import org.jetbrains.anko.backgroundColor

/**
 * Created by y0c021m on 5/23/18.
 */
class SettingsView (
        private val children: List<SettingsItem>,
        private val background: Int = Color.TRANSPARENT
) {
    fun inflate(params: InitParams): ViewGroup {
        val container = LinearLayout(params.context)

        container.orientation = LinearLayout.VERTICAL
        container.backgroundColor = this@SettingsView.background

        for (child: SettingsItem in children) {
            child.init(params)
            container.addView(child.build())
        }

        return container
    }
}