package com.shree.longrunningtask.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * Created by Shreedhar on 16,May,2019
 */
class MainThreadExecutor : Executor {
    private val handler: Handler = Handler(Looper.getMainLooper())
    override fun execute(runnable: Runnable?) {
        handler.post(runnable)
    }
}