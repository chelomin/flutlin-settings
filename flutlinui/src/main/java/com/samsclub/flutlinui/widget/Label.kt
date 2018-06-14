package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.view.View
import com.samsclub.flutlinui.base.SettingsItem
import com.samsclub.flutlinui.base.Text
import com.samsclub.flutlinui.base.TextFromLiveDataString
import com.samsclub.flutlinui.base.dp
import com.samsclub.flutlinui.style.DefStyles
import com.samsclub.flutlinui.style.LTRB
import org.jetbrains.anko.*

/**
 * Created by y0c021m on 5/23/18.
 */
class Label(
        val text: Text,
        private val textColor: MutableLiveData<Int>? = null,
        private val textSize: MutableLiveData<Int>? = null,
        private val padding: LTRB? = null,
        private val allCaps: Boolean = false,
        val visibility: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {
        val t = this@Label.text
        root = with(ip.context) {
            relativeLayout {
                textView {
                    text = t.get(context)
                    if (t is TextFromLiveDataString) {
                        val textObserver = Observer<String> {it -> text = it}
                        t.ldStr.observe(ip.lifecycleOwner, textObserver)
                    }

                    val textColorObserver = Observer<Int> { color -> setTextColor(color!!) }
                    val textSizeObserver = Observer<Int> { size -> textSize = (size ?: DefStyles.defTextSize).toFloat() }

                    this@Label.textColor?.observe(ip.lifecycleOwner, textColorObserver)
                    this@Label.textSize?.observe(ip.lifecycleOwner, textSizeObserver)

                    allCaps = this@Label.allCaps
                }.lparams(width = wrapContent) {
                    centerVertically()
                }
            }
        }

        applyPadding(root, padding)

        onBuilt()

        return root
    }
}