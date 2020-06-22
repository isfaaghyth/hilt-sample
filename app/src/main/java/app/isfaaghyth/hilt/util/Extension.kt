package app.isfaaghyth.hilt.util

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.showWithCondition(state: Boolean) {
    visibility = if (state) View.VISIBLE else View.GONE
}

fun View.hide() {
    visibility = View.GONE
}