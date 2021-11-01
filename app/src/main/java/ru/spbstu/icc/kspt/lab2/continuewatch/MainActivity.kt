package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
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
        Log.i("test", "onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("time", secondsElapsed)
        Log.i("test", "onSave $secondsElapsed")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt("time")
        Log.i("test", "onRestore $secondsElapsed")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        Log.i("test", "onPause")
        stopped = true
    }

    override fun onResume() {
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
