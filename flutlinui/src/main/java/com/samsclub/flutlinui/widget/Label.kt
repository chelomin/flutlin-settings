package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.view.View
import com.samsclub.flutlinui.base.SettingsItem
import com.samsclub.flutlinui.base.Text
import com.samsclub.flutlinui.base.TextFromLiveDataString
import org.jetbrains.anko.*

/**
 * Created by y0c021m on 5/23/18.
 */
class Label(
        val text: Text,
        var visibility: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {
        val t = this@Label.text
        root = with(context) {
            relativeLayout {
                textView {
                    text = t.get(context)
                    if (t is TextFromLiveDataString) {
                        val textObserver = Observer<String> {it -> text = it}
                        t.ldStr.observe(lifecycleOwner, textObserver)
                    }
                }.lparams(width = wrapContent) {
                    centerVertically()
                    margin = dip(8)
                }
            }
        }

        onBuilt()

        return root
    }
}