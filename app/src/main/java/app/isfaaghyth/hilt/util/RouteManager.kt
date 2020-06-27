package app.isfaaghyth.hilt.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object RouteManager {

    fun route(context: Context?, appLink: String) {
        val uri = Uri.parse(appLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context?.startActivity(intent)
    }

}