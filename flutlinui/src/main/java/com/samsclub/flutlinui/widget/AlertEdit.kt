package com.samsclub.flutlinui.widget

import android.app.AlertDialog
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.view.ViewCompat
import android.support.v7.widget.AppCompatEditText
import android.view.View
import android.widget.TextView
import com.samsclub.flutlinui.R
import com.samsclub.flutlinui.base.SettingsItem
import com.samsclub.flutlinui.base.Text
import com.samsclub.flutlinui.base.TextFromLiveDataString
import com.samsclub.flutlinui.style.DefStyles
import com.samsclub.flutlinui.style.LTRB
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by y0c021m on 5/23/18.
 */
class AlertEdit(
        val text: MutableLiveData<String>,
        private val textColor: MutableLiveData<Int>? = null,
        private val textSize: MutableLiveData<Int>? = null,
        private val editButtonText: Text? = null,
        private val positiveText: Text? = null,
        private val negativeText: Text? = null,
        private val titleText: Text? = null,
        private val editButtonColor: MutableLiveData<Int>? = null,
        private val validTintColor: MutableLiveData<Int>? = null,
        private val invalidTintColor: MutableLiveData<Int>? = null,
        private val padding: LTRB? = null,
        private val dialogPadding: LTRB? = null,
        private val allCaps: Boolean = false,
        /**
         * Return null if valid input, error message otherwise
         */
        private val validator: (String) -> String?,
        visibility: MutableLiveData<Boolean>? = null
) : SettingsItem(visibility) {
    override fun build(): View {
        val t = this@AlertEdit.text
        root = with(ip.context) {
            relativeLayout() {
                textView {
                    text = t.value
                    t.observe(ip.lifecycleOwner, Observer<String> { it -> text = it })

                    val textColorObserver = Observer<Int> { color -> setTextColor(color!!) }
                    val textSizeObserver = Observer<Int> { size ->
                        textSize = (size ?: DefStyles.defTextSize).toFloat()
                    }

                    this@AlertEdit.textColor?.observe(ip.lifecycleOwner, textColorObserver)
                    this@AlertEdit.textSize?.observe(ip.lifecycleOwner, textSizeObserver)

                    allCaps = this@AlertEdit.allCaps
                }.lparams(width = wrapContent) {
                    alignParentStart()
                }

                if (editButtonText != null) {
                    textView {
                        text = editButtonText.get(context)
                        if (editButtonText is TextFromLiveDataString) {
                            editButtonText.ldStr.observe(ip.lifecycleOwner, Observer<String> { it -> text = it })
                        }
                        editButtonColor?.observe(ip.lifecycleOwner, Observer<Int> { it ->
                            if (it != null) {
                                textColor = it
                            }
                        })
                    }.lparams(width = wrapContent) {
                        alignParentEnd()
                    }
                }
            }
        }

        applyPadding(root, padding)

        root.onClick { showDialog() }

        onBuilt()

        return root
    }

    private fun showDialog() {
        val dlgRoot = ip.inflater.inflate(R.layout.dialog_alert_edit, null)
        val title = dlgRoot.findViewById<TextView>(R.id.title)
        val editText = dlgRoot.findViewById<AppCompatEditText>(R.id.edit_text)
        val errorText = dlgRoot.findViewById<TextView>(R.id.error_text)

        title.text = titleText?.get(ip.context) ?: "Please enter new value"
        editText.setText(text.value)

        if (dialogPadding != null) {
            with (dialogPadding) {
                dlgRoot.setPadding(left, top, right, bottom)
            }
        }

        val positive = positiveText?.get(ip.context) ?: "Ok"
        val negative = negativeText?.get(ip.context) ?: "Cancel"

        val dialog = AlertDialog.Builder(ip.context)
                .setView(dlgRoot)
                .setPositiveButton(positive, null)
                .setNegativeButton(negative, null)
                .create()

        dialog.setOnShowListener {
            val saveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val cancelButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

            cancelButton.setOnClickListener { dialog.dismiss() }
            saveButton.setOnClickListener {
                val value = editText.text.toString()
                when (validator(value)) {
                    null -> {
                        text.value = value
                        dialog.dismiss()
                    }
                    else -> {
                        editText.afterTextChanged { validate(editText, errorText) }
                        validate(editText, errorText)
                    }
                }
            }
            validate(editText, errorText)
        }

        dialog.show()
    }

    private fun validate(editText: AppCompatEditText, errorText: TextView) {
        val invalidColor = invalidTintColor?.value ?: Color.RED
        val colorStateListValid = ColorStateList.valueOf(validTintColor?.value ?: Color.BLUE)
        val colorStateListInvalid = ColorStateList.valueOf(invalidColor)

        val error = validator(editText.text.toString())
        when (error) {
            null -> {
                errorText.visibility = View.INVISIBLE
                ViewCompat.setBackgroundTintList(editText, colorStateListValid)
            }
            else -> {
                errorText.visibility = View.VISIBLE
                errorText.text = error
                errorText.textColor = invalidColor
                ViewCompat.setBackgroundTintList(editText, colorStateListInvalid)
            }
        }
    }
}