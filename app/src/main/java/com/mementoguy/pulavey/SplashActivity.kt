package com.mementoguy.pulavey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        actionBar?.hide()

        navigateToMainActivity()
    }

    fun navigateToMainActivity() {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(mainActivityIntent)
        }, 1200)
    }
}