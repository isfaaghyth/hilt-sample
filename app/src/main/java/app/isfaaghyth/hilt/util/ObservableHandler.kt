package app.isfaaghyth.hilt.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


object ObservableHandler {

    val IO: ExecutorService = Executors.newFixedThreadPool(4)

    val Main by lazy(LazyThreadSafetyMode.NONE) {
        Handler(Looper.getMainLooper())
    }

}