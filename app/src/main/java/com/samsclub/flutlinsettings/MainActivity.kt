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
        viewModel.editableText2.value = ""

        val container = findViewById<FrameLayout>(R.id.root)
        val cardMargins = Style.cardMargins

        Log.d("yury", "cardMargins = ${cardMargins}")
        val settingsView = SettingsView(
                listOf(
                        Label(
                                text = Text.from(viewModel.editableText),
                                padding = DefStyles.defPadding
                        ),
                        Box(
                                child = Label(
                                        text = Text.from("Snap again!"),
                                        textColor = mld(Color.RED),
                                        visibility = viewModel.toggleData
                                ),
                                bgColor = mld(Color.YELLOW),
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
                                hint = Text.from("Hint2")
                        ),
                        Card(
                                view = SettingsView(
                                        listOf(
                                                Label(
                                                        text = Text.from("Some label"),
                                                        allCaps = true,
                                                        textColor = mld(Color.BLUE),
                                                        textSize = mld(8)
                                                ),
                                                InlineEdit(
                                                        text = viewModel.editableText2,
                                                        hint = Text.from("Some other hint")
                                                )
                                        )
                                ),
                                margin = cardMargins.margin,
                                padding = cardMargins.padding
                        )
                )
        ).inflate(InitParams(
                applicationContext,
                LayoutInflater.from(applicationContext),
                this as LifecycleOwner))

        container.addView(settingsView)
    }
}
