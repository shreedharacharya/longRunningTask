package com.shree.longrunningtask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.shree.longrunningtask.utils.DefaultExecutorSupplier

class MainActivity : AppCompatActivity() {
 var tv :TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.to_display)
        doSomeBackgroundWork()
    }

    fun doSomeBackgroundWork() {
        val start = System.nanoTime()

        val task1 = DefaultExecutorSupplier.getInstance().forBackgroundTask().execute {
            var count = countPrime(100000)
            DefaultExecutorSupplier.getInstance().forMainThreadTask().execute{
                tv?.append("$count ")
            }

            Log.e("Prime1", "$count and core= ${DefaultExecutorSupplier.getInstance().numberOfCores}")
        }
        val task2 = DefaultExecutorSupplier.getInstance().forBackgroundTask().execute {
            var count = countPrime(200000)
            DefaultExecutorSupplier.getInstance().forMainThreadTask().execute{
                tv?.append("$count ")
            }
            Log.e("Prime2", "$count and core= ${DefaultExecutorSupplier.getInstance().numberOfCores}")
        }
        val task3 = DefaultExecutorSupplier.getInstance().forBackgroundTask().execute {
            var count = countPrime(300000)
            DefaultExecutorSupplier.getInstance().forMainThreadTask().execute{
                tv?.append("$count ")
            }
            Log.e("Prime3", "$count and core= ${DefaultExecutorSupplier.getInstance().numberOfCores}")
        }
        val task4 = DefaultExecutorSupplier.getInstance().forBackgroundTask().execute {
            var count = countPrime(400000)
            DefaultExecutorSupplier.getInstance().forMainThreadTask().execute{
                tv?.append("$count ")
            }
            Log.e("Prime4", "$count and core= ${DefaultExecutorSupplier.getInstance().numberOfCores}")
        }
        val task5 = DefaultExecutorSupplier.getInstance().forBackgroundTask().execute {
            var count = countPrime(500000)
            DefaultExecutorSupplier.getInstance().forMainThreadTask().execute{
                tv?.append("$count ")
            }
            Log.e("Prime5", "$count and core= ${DefaultExecutorSupplier.getInstance().numberOfCores}")
        }
        val task6 = DefaultExecutorSupplier.getInstance().forBackgroundTask().execute {
            var count = countPrime(600000)
            DefaultExecutorSupplier.getInstance().forMainThreadTask().execute{
                tv?.append("$count ")
            }
            Log.e("Prime6", "$count and core= ${DefaultExecutorSupplier.getInstance().numberOfCores}")

        }
        val task7 = DefaultExecutorSupplier.getInstance().forBackgroundTask().execute {
            var count = countPrime(700000)
            DefaultExecutorSupplier.getInstance().forMainThreadTask().execute{
                tv?.append("$count ")
            }
            Log.e("Prime7", "$count and core= ${DefaultExecutorSupplier.getInstance().numberOfCores}")
        }

        val task8 = DefaultExecutorSupplier.getInstance().forBackgroundTask().execute {
            var count = countPrime(800000)
            DefaultExecutorSupplier.getInstance().forMainThreadTask().execute{
                tv?.append("$count ")
            }
            Log.e("Prime8", "$count and core= ${DefaultExecutorSupplier.getInstance().numberOfCores}")
        }
        val task9 = DefaultExecutorSupplier.getInstance().forBackgroundTask().execute {
            var count = countPrime(100000)
            Log.e("Prime9", "$count and core= ${DefaultExecutorSupplier.getInstance().numberOfCores}")
            val end = System.nanoTime()
            val time = end - start
            val seconds = time  / 1_000_000_000.0
            Log.e("PrimeRESULT", "$seconds")
            //3.129003
            //5.163731

        }

      //  DefaultExecutorSupplier.getInstance().forBackgroundTask().shutdown()

       // DefaultExecutorSupplier.getInstance().forBackgroundTask().awaitTermination(5,TimeUnit.NANOSECONDS)

    }


    private fun countPrime(limit: Int): Int {
        var count = 0
        for (i in 1..limit) {

            var isPrime = true

            //check to see if the number is prime
            for (j in 2 until i) {

                if (i % j == 0) {
                    isPrime = false
                    break
                }
            }

            if (isPrime)
                count++
        }
        return count
    }
}
