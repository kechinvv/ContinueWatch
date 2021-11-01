package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivityP : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private var stopped = false

    private var backgroundThread = Thread {
        while (true) {
            if (!stopped) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text =
                        String.format(getString(R.string.seconds), secondsElapsed++);
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
        prefs = getPreferences(Context.MODE_PRIVATE)
        Log.i("test", "onCreate")
    }


    override fun onPause() {
        super.onPause()
        Log.i("test", "onPause")
        prefs.edit().putInt(getString(R.string.time),secondsElapsed).apply()
        stopped = true
    }

    override fun onResume() {
        secondsElapsed = prefs.getInt(getString(R.string.time),0)
        super.onResume()
        Log.i("test", "onResume")
        stopped = false
    }

    override fun onStart() {
        super.onStart()
        Log.i("test", "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.i("test", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("test", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("test", "onDestroy")
    }
}
