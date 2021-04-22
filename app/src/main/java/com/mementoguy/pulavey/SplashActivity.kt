package com.mementoguy.pulavey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.mementoguy.pulavey.databinding.ActivitySplashBinding
import com.mementoguy.pulavey.survey.SurveyActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.hide()

        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        val mainActivityIntent = Intent(this, SurveyActivity::class.java)
        Handler().postDelayed({
            startActivity(mainActivityIntent)
        }, 1200)
    }
}