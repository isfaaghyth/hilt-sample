package app.isfaaghyth.hilt.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object DateFormatter {

    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("d MMM yyyy HH:mm:ss")

    fun format(timestamp: Long): String {
        return formatter.format(Date(timestamp))
    }

}
