package com.samsclub.flutlinsettings

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.samsclub.flutlinsettings.style.Style
import com.samsclub.flutlinsettings.viewmodel.SettingsVm
import com.samsclub.flutlinui.SettingsView
import com.samsclub.flutlinui.base.InitParams
import com.samsclub.flutlinui.base.Text
import com.samsclub.flutlinui.base.dp
import com.samsclub.flutlinui.base.mld
import com.samsclub.flutlinui.style.DefStyles
import com.samsclub.flutlinui.style.LTRB
import com.samsclub.flutlinui.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsVm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(SettingsVm::class.java)
        viewModel.editableText.value = "Snap!"
        viewModel.editableText2.value = "Continuous update text"

        val container = findViewById<FrameLayout>(R.id.root)
        val cardMargins = Style.cardMargins

        SettingsView(
            listOf(
                Label(
                    text = Text.from(viewModel.editableText),
                    padding = DefStyles.defPadding
                ),
                Box(
                    child = Label(
                        text = Text.from(viewModel.editableText2),
                        textColor = mld(Color.RED)
                    ),
                    bgColor = mld(Color.YELLOW),
                    visibility = viewModel.toggleData,
                    boxParams = BoxParams(margin = LTRB(0), padding = LTRB(dp(4)))
                ),
                Check(
                    text = Text.from("Snap toggle"),
                    data = viewModel.toggleData
                ),
                InlineEdit(
                    text = viewModel.editableText,
                    hint = Text.from("Hint1")
                ),
                InlineEdit(
                    text = viewModel.editableText2,
                    hint = Text.from("Hint2"),
                    continuousUpdate = true
                ),
                Card(
                    view = SettingsView(
                        listOf(
                            Label(
                                text = Text.from("Some label"),
                                allCaps = true,
                                padding = LTRB(dp(8)),
                                textColor = mld(Color.BLUE),
                                textSize = mld(14)
                            ),
                            InlineEdit(
                                text = viewModel.editableText2,
                                hint = Text.from("Some other hint"),
                                continuousUpdate = true
                            ),
                            AlertEdit(
                                text = mld("Initial value"),
                                textColor = mld(Color.MAGENTA),
                                textSize = mld(16),
                                positiveText = Text.from("Save"),
                                negativeText = Text.from("Discard"),
                                titleText = Text.from("What are you up for?"),
                                padding = LTRB(dp(8)),
                                dialogPadding = LTRB(dp(8)),
                                editButtonText = Text.from(mld("Change")),
                                editButtonColor = mld(Color.BLUE),
                                validator = { it -> if (it.length < 8) "Please enter at least 8 characters" else null }
                            ),
                            Divider(
                                color = mld(Color.DKGRAY),
                                padding = LTRB(dp(8)),
                                height = dp(2)
                            ),
                            Page(
                                widget = Label(
                                    text = Text.from("Next page of settings"),
                                    allCaps = true,
                                    padding = LTRB(dp(8)),
                                    textColor = mld(Color.BLUE),
                                    textSize = mld(14)
                                ),
                                page = buildSecondPage()
                            )
                        )
                    ),
                    margin = cardMargins.margin,
                    padding = cardMargins.padding
                )
            )
        ).run(
            InitParams(
                this,
                LayoutInflater.from(this),
                this as LifecycleOwner,
                container)
        )
    }

    private fun buildSecondPage(): SettingsView {
        return SettingsView (
            children = listOf (
                Label(
                    text = Text.from("Page 2"),
                    allCaps = true,
                    padding = LTRB(dp(8)),
                    textColor = mld(Color.BLUE),
                    textSize = mld(14)
                ),
                Card(
                    view = SettingsView(
                        listOf(
                            Label(
                                text = Text.from("Card"),
                                allCaps = true,
                                padding = LTRB(dp(8)),
                                textColor = mld(Color.BLUE),
                                textSize = mld(14)
                            ),
                            InlineEdit(
                                text = viewModel.editableText2,
                                hint = Text.from("Enter your name"),
                                continuousUpdate = true
                            ),
                            AlertEdit(
                                text = mld("zuck@fb.com"),
                                textColor = mld(Color.BLUE),
                                textSize = mld(16),
                                positiveText = Text.from("Save"),
                                negativeText = Text.from("Discard"),
                                titleText = Text.from("What's your email address?"),
                                padding = LTRB(dp(8)),
                                dialogPadding = LTRB(dp(8)),
                                editButtonText = Text.from(mld("Change")),
                                editButtonColor = mld(Color.BLUE),
                                validator = { it -> if (it.length < 8) "Please enter at least 8 characters" else null }
                            ),
                            Divider(
                                color = mld(Color.YELLOW),
                                padding = LTRB(dp(8)),
                                height = dp(2)
                            )
//                            Page(
//                                widget = Label(
//                                    text = Text.from("Next page of settings"),
//                                    allCaps = true,
//                                    padding = LTRB(dp(8)),
//                                    textColor = mld(Color.BLUE),
//                                    textSize = mld(14)
//                                ),
//                                page = buildSecondPage()
//                            )
                        )
                    ),
                    margin = Style.cardMargins.margin,
                    padding = Style.cardMargins.padding
                )
            )
        )
    }
}
