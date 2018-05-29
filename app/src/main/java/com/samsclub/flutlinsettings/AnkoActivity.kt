package com.samsclub.flutlinsettings

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.jetbrains.anko.*

class AnkoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsUI().setContentView(this)
    }

    class SettingsUI : AnkoComponent<AnkoActivity> {
        override fun createView(ui: AnkoContext<AnkoActivity>) =
                with(ui) {
                    verticalLayout {
                        textView {
                            text = "test"
                        }.lparams(width = wrapContent) {
                            margin = dip (16)
                        }

                    }
                }
    }
}

