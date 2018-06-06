package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.samsclub.flutlinui.base.SettingsItem
import com.samsclub.flutlinui.base.Text
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange

/**
 * Created by y0c021m on 5/25/18.
 */
class Check (
        val text: Text,
        visibility: MutableLiveData<Boolean>? = null,
        val data: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {
        root = with(context) {
            relativeLayout {
                checkBox {
                    text = this@Check.text.get(context)
                    isChecked = data?.value ?: true
                    onCheckedChange { _, isChecked -> data?.value = isChecked }
                }.lparams(width = wrapContent) {
                    centerVertically()
                }
            }
        }

        onBuilt()

        return root
    }
}