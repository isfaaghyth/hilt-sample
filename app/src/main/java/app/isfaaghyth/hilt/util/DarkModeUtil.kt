package app.isfaaghyth.hilt.util

import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate

object DarkModeUtil {

    fun darkModeDelegate(resources: Resources) {
        val uiMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        AppCompatDelegate.setDefaultNightMode(
            if (uiMode == Configuration.UI_MODE_NIGHT_NO) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            }
        )
    }

}