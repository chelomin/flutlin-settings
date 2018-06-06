package com.samsclub.flutlinui.style

import com.samsclub.flutlinui.base.dp
import com.samsclub.flutlinui.widget.BoxParams

/**
 * Created by y0c021m on 6/6/18.
 */

object DefStyles {
    const val defTextSize = 10
    
    val defMargin = LTRB(dp(4))
    val defPadding = LTRB(dp(4))

    val defBoxParams = BoxParams(
            margin = defMargin,
            padding = defPadding
    )
}

/**
 * LTRB = left, top, right, bottom; eg. for use as margins or padding spec
 */
data class LTRB (
        val left: Int = 0,
        val top: Int = 0,
        val right: Int = 0,
        val bottom: Int = 0
) {
    constructor(all: Int = 0) : this (left = all, top = all, right = all, bottom = all)
}