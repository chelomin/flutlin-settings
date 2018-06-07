package com.samsclub.flutlinui.widget

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.samsclub.flutlinui.base.SettingsItem
import com.samsclub.flutlinui.base.Text
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onEditorAction
import org.jetbrains.anko.sdk25.coroutines.onFocusChange

/**
 * Created by y0c021m on 5/23/18.
 */
class InlineEdit(
        val text: MutableLiveData<String>,
        val hint: Text? = null,
        private val inputType: Int = EditorInfo.TYPE_CLASS_TEXT,
        private val imeOptions: Int = EditorInfo.IME_ACTION_DONE,
        private val maxLines: Int = 1,
        private val continuousUpdate: Boolean = false,
        visibility: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {
        root = with(context) {
            relativeLayout {
                val et = editText {
                    hint = this@InlineEdit.hint?.get(context) ?: ""
                }.lparams(width = matchParent) {
                    centerVertically()
                }
                et.maxLines = this@InlineEdit.maxLines
                et.inputType = this@InlineEdit.inputType
                et.imeOptions = this@InlineEdit.imeOptions
                et.setText(this@InlineEdit.text.value ?: "")
                et.onEditorAction { _, actionId, _ ->
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE -> text.value = et.text.toString()
                    }
                }
                et.onFocusChange { _, hasFocus -> if (!hasFocus) text.value = et.text.toString() }

                if (continuousUpdate) {
                    et.afterTextChanged { text -> this@InlineEdit.text.value = text }
                }
            }
        }

        onBuilt()

        return root
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}