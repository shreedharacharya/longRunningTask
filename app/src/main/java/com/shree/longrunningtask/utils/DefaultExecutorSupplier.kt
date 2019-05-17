package com.shree.longrunningtask.utils

import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by Shreedhar on 16,May,2019
 */
class DefaultExecutorSupplier private constructor() {

    companion object {
        private var sInstance: DefaultExecutorSupplier? = null

        /*
       * returns the instance of DefaultExecutorSupplier
       */
        fun getInstance(): DefaultExecutorSupplier {
            if (sInstance == null) {
                synchronized(
                    this
                ) {
                    sInstance = DefaultExecutorSupplier()
                }
            }
            return sInstance as DefaultExecutorSupplier

        }
    }

    /*
    * Number of cores to decide the number of threads
    */
     val numberOfCores = Runtime.getRuntime().availableProcessors()

    /*
    * thread pool executor for background tasks
    */
    private val mForBackgroundTasks: ThreadPoolExecutor

    /*
    * thread pool executor for light weight background tasks
    */
    private val mForLightWeightBackgroundTasks: ThreadPoolExecutor

    /*
    * thread pool executor for main thread tasks
    */
    private val mMainThreadExecutor: Executor


    init {
        // setting the thread pool executor for mForBackgroundTasks
        mForBackgroundTasks = ThreadPoolExecutor(
            (numberOfCores * 2) +1,
            (numberOfCores * 2)+1,
            60L,
            TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>()
        )

        //setting the thread pool executor for mForLightWightBackgroundTasks
        mForLightWeightBackgroundTasks = ThreadPoolExecutor(
            numberOfCores * 2,
            numberOfCores * 2,
            60L,
            TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>()
        )

        //setting the thread pool executor for mMainThreadExecutor
        mMainThreadExecutor = MainThreadExecutor()
    }

    fun forBackgroundTask(): ThreadPoolExecutor {
        return mForBackgroundTasks
    }

    fun forLightWightBackgroundTask(): ThreadPoolExecutor {
        return mForLightWeightBackgroundTasks
    }

    fun forMainThreadTask(): Executor {
        return mMainThreadExecutor
    }


}