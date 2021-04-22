package com.mementoguy.pulavey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        navigateToMainActivity()
    }

    fun navigateToMainActivity() {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(mainActivityIntent)
        }, 1000)
    }
}