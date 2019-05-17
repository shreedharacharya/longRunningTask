package com.shree.longrunningtask.utils

import android.os.Process
import java.util.concurrent.ThreadFactory

/**
 * Created by Shreedhar on 16,May,2019
 */
class PriorityThreadFactory(threadPriority: Int) : ThreadFactory {
    private val mThreadPriority = threadPriority
    override fun newThread(runnable: Runnable?): Thread {
        val wrapperRunnable = Runnable {
            try {
                Process.setThreadPriority(mThreadPriority)
            } catch (t: Throwable) {

            }

            runnable?.run()
        }

        return Thread(wrapperRunnable)
    }
}